package com.cl.cocde.gateway.log;

import com.alibaba.fastjson.JSON;
import com.cl.cocde.gateway.util.IpUtil;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author chengliang
 * @since 2022/10/22 20:01
 */
public class GatewayLogHandler {

    public static GatewayLog handle(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        // 获取路由信息
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        assert route != null;

        // 请求路径
        String requestPath = request.getPath().pathWithinApplication().value();
        // ip
        String ipAddr = IpUtil.getIpAddr(request);
        // 请求类型
        String methodValue = request.getMethodValue();
        // 协议
        String scheme = request.getURI().getScheme();
        // 请求头
        String headers = JSON.toJSONString(request.getHeaders().toSingleValueMap());
        // media-type
        MediaType mediaType = request.getHeaders().getContentType();
        String requestContentType = mediaType != null ? mediaType.getType() : null;

        GatewayLog gatewayLog = new GatewayLog();
        gatewayLog.setTargetServer(route.getUri().toString());
        gatewayLog.setRequestPath(requestPath);
        gatewayLog.setRequestMethod(methodValue);
        gatewayLog.setRequestContentType(requestContentType);
        gatewayLog.setScheme(scheme);
        gatewayLog.setHeaders(headers);
        gatewayLog.setIp(ipAddr);
//        log.setCity();
        gatewayLog.setStartTime(System.currentTimeMillis());
//        log.setRequestTime();
//        log.setResponseTime();
//        log.setRouteConfig();
//        log.setStatus();

        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
        gatewayLog.setRequestBody(getUrlParamsByMap(queryParams));
        return gatewayLog;
    }

    public static Publisher<? extends DataBuffer> handle(ServerWebExchange exchange, Publisher<? extends DataBuffer> body, GatewayLog gatewayLog) {
        // 计算执行时间
        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory = response.bufferFactory();
        long endTime = System.currentTimeMillis();
        long executeTime = (endTime - gatewayLog.getStartTime());
        gatewayLog.setEndTime(endTime);
        gatewayLog.setExecuteTime(executeTime);

        // 状态
        HttpStatus statusCode = response.getStatusCode();
        boolean isSuccess = Objects.equals(response.getStatusCode(), HttpStatus.OK);
        if (statusCode != null) {
            gatewayLog.setStatus(isSuccess ? "成功" : "失败");
        }
        String originalResponseContentType = exchange.getAttribute(ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);


        if (isSuccess && StringUtils.hasText(originalResponseContentType) && originalResponseContentType.contains(MediaType.APPLICATION_JSON.getType())) {
            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
            return fluxBody.buffer().map(dataBuffers -> {

                // 合并多个流集合，解决返回体分段传输
                DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                DataBuffer join = dataBufferFactory.join(dataBuffers);
                byte[] content = new byte[join.readableByteCount()];
                join.read(content);

                // 释放掉内存
                DataBufferUtils.release(join);
                String responseResult = new String(content, StandardCharsets.UTF_8);

                gatewayLog.setResponseData(responseResult);

                return bufferFactory.wrap(content);
            });
        }
        return body;
    }

    public static List<HttpMessageReader<?>> messageReaders() {
        return HandlerStrategies.withDefaults().messageReaders();
    }

    public static String getUrlParamsByMap(MultiValueMap<String, String> map) {
        if (ObjectUtils.isEmpty(map)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue().get(0));
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.lastIndexOf("&"));
        }
        return s;
    }

}
