package com.cl.code.common.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 启用状态 枚举
 *
 * @author chengliang
 * @since 2022/11/7 0:00
 */
@Getter
@AllArgsConstructor
public enum EnableEnum {

    /**
     * 停用
     */
    DISABLE(0, "停用"),

    /**
     * 启用
     */
    ENABLE(1, "启用"),
    ;

    private final Integer type;

    private final String desc;

}
