package com.cl.code.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cl.code.common.datasource.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色_权限
 * </p>
 *
 * @author chengliang
 * @since 2022-11-06
 */
@Getter
@Setter
@TableName("cl_role_permission_relation")
public class ClRolePermissionRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 权限id
     */
    @TableField("permission_id")
    private Long permissionId;


    public static final String ROLE_ID = "role_id";

    public static final String PERMISSION_ID = "permission_id";

}
