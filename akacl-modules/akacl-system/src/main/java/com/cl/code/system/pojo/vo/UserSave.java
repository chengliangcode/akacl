package com.cl.code.system.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author chengliang
 * @since 2022/11/9 0:41
 */
@Data
public class UserSave {

    /**
     * id
     */
    private Long id;

    /**
     * username
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /**
     * password
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * 昵称
     */
    private String nikeName;

    /**
     * phone
     */
    private String phone;

    /**
     * email
     */
    private String email;

}
