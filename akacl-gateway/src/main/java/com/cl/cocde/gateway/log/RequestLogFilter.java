package com.cl.cocde.gateway.log;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.function.Function;

/**
 * @author chengliang
 * @since 2022/10/22 17:20
 */
@Slf4j
@Component
public class RequestLogFilter implements GlobalFilter, Ordered {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        GatewayLog gatewayLog = GatewayLogHandler.handle(exchange);

        HttpHeaders headers = exchange.getRequest().getHeaders();
        MediaType mediaType = headers.getContentType();

        Function<String, Mono<? extends String>> function;
        if (mediaType != null && (MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(mediaType) || MediaType.APPLICATION_JSON.isCompatibleWith(mediaType))) {
            function = requestBody -> {
                gatewayLog.setRequestBody(requestBody);
                return Mono.just(requestBody);
            };
        } else {
            function = Mono::just;
        }

        ServerRequest serverRequest = ServerRequest.create(exchange, GatewayLogHandler.messageReaders());
        Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).flatMap(function);
        // 通过 BodyInserter 插入 body(支持修改body), 避免 request body 只能获取一次
        BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);

        HttpHeaders httpHeaders = new HttpHeaders();
        headers.keySet().forEach(k -> httpHeaders.set(k, headers.getFirst(k)));

        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, httpHeaders);
        return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
            // 重新封装请求
            ServerHttpRequest decoratedRequest = requestDecorate(exchange, httpHeaders, outputMessage);

            // 记录响应日志
            ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, gatewayLog);

            // 记录普通的
            return chain.filter(exchange.mutate().request(decoratedRequest).response(decoratedResponse).build()).then(Mono.fromRunnable(() -> {
                // 打印日志
                writeAccessLog(gatewayLog);
            }));
        }));

    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

    private ServerHttpResponseDecorator recordResponseLog(ServerWebExchange exchange, GatewayLog gatewayLog) {
        ServerHttpResponse response = exchange.getResponse();
        return new ServerHttpResponseDecorator(response) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                return super.writeWith(GatewayLogHandler.handle(exchange, body, gatewayLog));
            }
        };
    }

    private ServerHttpRequestDecorator requestDecorate(ServerWebExchange exchange, HttpHeaders headers, CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(super.getHeaders());
                if (contentLength > 0) {
                    httpHeaders.setContentLength(contentLength);
                } else {
                    // TODO: this causes a 'HTTP/1.1 411 Length Required' // on
                    // httpbin.org
                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                }
                return httpHeaders;
            }

            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }

    private void writeAccessLog(GatewayLog gatewayLog) {
        applicationEventPublisher.publishEvent(new GatewayLogEvent(gatewayLog));
    }

}
