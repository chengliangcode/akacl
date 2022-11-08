package com.cl.code.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cl.code.common.datasource.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author chengliang
 * @since 2022-11-06
 */
@Getter
@Setter
@TableName("cl_permission")
public class ClPermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 权限编码
     */
    @TableField("permission_code")
    private String permissionCode;

    /**
     * 权限名称
     */
    @TableField("permission_name")
    private String permissionName;


    public static final String PERMISSION_CODE = "permission_code";

    public static final String PERMISSION_NAME = "permission_name";

}
