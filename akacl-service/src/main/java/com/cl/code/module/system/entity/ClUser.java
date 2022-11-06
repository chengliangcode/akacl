package com.cl.code.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cl.code.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author chengliang
 * @since 2022-11-06
 */
@Getter
@Setter
@TableName("cl_user")
public class ClUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * username
     */
    @TableField("username")
    private String username;

    /**
     * password
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nike_name")
    private String nikeName;

    /**
     * phone
     */
    @TableField("phone")
    private String phone;

    /**
     * email
     */
    @TableField("email")
    private String email;


    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String NIKE_NAME = "nike_name";

    public static final String PHONE = "phone";

    public static final String EMAIL = "email";

}
