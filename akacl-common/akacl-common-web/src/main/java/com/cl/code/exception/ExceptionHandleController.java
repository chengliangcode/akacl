package com.cl.code.exception;

import com.cl.code.common.core.result.ApiResult;
import com.cl.code.common.core.result.ApiResultConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author chengliang
 * @since 2022/11/12 16:34
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandleController {

    private ApiResult<String> baseHandle(Exception exception, ApiResultConstant resultConstant, String message) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw, true));
        String errorMessage = sw.getBuffer().toString();
        log.error("\r\n" + errorMessage);
        return ApiResult.build(resultConstant, message == null ? resultConstant.getDesc() : message, errorMessage);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<String> handle(Exception exception) {
        String message = null;
        if (exception instanceof NullPointerException) {
            message = "空指针异常";
        } else if (exception instanceof MethodArgumentNotValidException) {
            message = "请求参数异常";
        }
        return baseHandle(exception, ApiResultConstant.SERVER_ERROR, message);
    }


}
