package com.cl.code.common.core.result;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author chengliang
 * @since 2022/11/12 14:35
 */
@Getter
@EqualsAndHashCode
public class ApiResult<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

    private ApiResult() {
    }

    private ApiResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResult<T> build(Integer code, String msg, T data) {
        return new ApiResult<>(code, msg, data);
    }

    public static <T> ApiResult<T> build(ApiResultConstant resultConstant, T data) {
        return build(resultConstant.getCode(), resultConstant.getDesc(), data);
    }

    public static <T> ApiResult<T> build(ApiResultConstant resultConstant, String msg, T data) {
        return build(resultConstant.getCode(), msg, data);
    }

    public static <T> ApiResult<T> success(T data) {
        return build(ApiResultConstant.SUCCESS, data);
    }

}
