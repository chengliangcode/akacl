package com.cl.code.filter;

import com.cl.code.common.core.context.LogTraceIdContext;
import com.cl.code.common.core.util.IdGeneratorUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chengliang
 * @since 2022/11/16 17:30
 */
public class LogTraceIdRequestLoggingFilter extends AbstractRequestLoggingFilter {
    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        String requestId = request.getHeader(LogTraceIdContext.TRACE_ID_KEY);
        if (StringUtils.hasText(requestId)) {
            LogTraceIdContext.setTraceId(requestId);
        } else {
            LogTraceIdContext.setTraceId(IdGeneratorUtil.generateId().toString());
        }
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        LogTraceIdContext.removeTraceId();
    }

}
