package com.cl.code.common.core.context;

import com.cl.code.common.core.util.IdGeneratorUtil;
import org.slf4j.MDC;

/**
 * 日志追踪id 上下文
 *
 * @author chengliang
 * @since 2022/11/16 17:13
 */
public class LogTraceIdContext {

    public static final String TRACE_ID_KEY = "requestId";

    public static void setTraceId(String traceId) {
        if (traceId != null && !"".equals(traceId.trim())) {
            MDC.put(TRACE_ID_KEY, traceId);
        }
    }

    public static String getTraceId() {
        String traceId = MDC.get(TRACE_ID_KEY);
        return traceId == null ? "" : traceId;
    }

    public static String generateTraceId() {
        return IdGeneratorUtil.generateId().toString();
    }

    public static void removeTraceId() {
        MDC.remove(TRACE_ID_KEY);
    }

    public static void clearTraceId() {
        MDC.clear();
    }

}
