package com.cl.code.common.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chengliang
 * @since 2022/11/12 14:38
 */
@Getter
@AllArgsConstructor
public enum ApiResultConstant {

    SUCCESS(200, "请求成功"),

    NOT_AUTHENTICATION(401, "未身份认证"),

    NO_PERMISSION(403, "没有权限"),

    SERVER_ERROR(500, "服务异常"),
    ;

    private final Integer code;

    private final String desc;


}
