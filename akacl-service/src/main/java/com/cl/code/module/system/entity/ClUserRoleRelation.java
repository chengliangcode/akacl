package com.cl.code.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cl.code.common.datasource.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author chengliang
 * @since 2022-11-06
 */
@Getter
@Setter
@TableName("cl_user_role_relation")
public class ClUserRoleRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;


    public static final String USER_ID = "user_id";

    public static final String ROLE_ID = "role_id";

}
