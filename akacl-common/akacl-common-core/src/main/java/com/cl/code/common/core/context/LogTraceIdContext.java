package com.cl.code.common.core.context;

import com.cl.code.common.core.util.IdGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * 日志追踪id 上下文
 *
 * @author chengliang
 * @since 2022/11/16 17:13
 */
@Slf4j
public class LogTraceIdContext {

    public static final String TRACE_ID_KEY = "requestId";

    public static void setTraceId(String traceId) {
        if (traceId != null && !"".equals(traceId.trim())) {
            log.info("set traceId key: " + TRACE_ID_KEY + " value:" + traceId);
            MDC.put(TRACE_ID_KEY, traceId);
        }
    }

    public static String getTraceId() {
        String traceId = MDC.get(TRACE_ID_KEY);
        return traceId == null ? "" : traceId;
    }

    public static String generateTraceId() {
        String traceId = IdGeneratorUtil.generateId().toString();
        log.info("generate traceId key: " + TRACE_ID_KEY + " value:" + getTraceId());
        return traceId;
    }

    public static void removeTraceId() {
        log.info("clear traceId key: " + TRACE_ID_KEY + " value:" + getTraceId());
        MDC.remove(TRACE_ID_KEY);
    }

    public static void clearTraceId() {
        MDC.clear();
    }

}
