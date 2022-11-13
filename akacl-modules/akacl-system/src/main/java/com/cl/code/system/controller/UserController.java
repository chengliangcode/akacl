package com.cl.code.system.controller;

import com.cl.code.common.core.result.ApiResult;
import com.cl.code.system.pojo.vo.UserSave;
import com.cl.code.system.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户 Api
 *
 * @author chengliang
 * @since 2022/11/9 0:31
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public ApiResult<Long> userAdd(@Validated @RequestBody UserSave userSave) {
        String a = null;
        a.toString();
        return ApiResult.success(userService.userAdd(userSave));
    }

    @PostMapping("/edit")
    public boolean userEdit(@Validated @RequestBody UserSave userSave) {
        return userService.userEdit(userSave);
    }

}
