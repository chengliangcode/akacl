package com.cl.code.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 删除状态 枚举
 *
 * @author chengliang
 * @since 2022/11/7 0:03
 */
@Getter
@AllArgsConstructor
public enum DeleteEnum {
    
    /**
     * 未删除
     */
    UN_DELETE(0, "未删除"),

    /**
     * 已删除
     */
    DELETED(1, "已删除"),
    ;

    private final Integer type;

    private final String desc;

}
