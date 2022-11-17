package com.cl.cocde.gateway.log;

import com.cl.code.common.core.context.LogTraceIdContext;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author chengliang
 * @since 2022/11/17 15:26
 */
@Component
public class TraceLogFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest req = request.mutate().headers(httpHeaders -> {
            String traceId = LogTraceIdContext.generateTraceId();
            LogTraceIdContext.setTraceId(traceId);
            httpHeaders.set(LogTraceIdContext.TRACE_ID_KEY, traceId);
        }).build();
        exchange.mutate().request(req);
        return chain.filter(exchange).then(Mono.fromRunnable(LogTraceIdContext::removeTraceId));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
