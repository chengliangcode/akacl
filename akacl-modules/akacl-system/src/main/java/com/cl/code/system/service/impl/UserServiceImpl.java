package com.cl.code.system.service.impl;

import com.cl.code.module.system.entity.ClUser;
import com.cl.code.module.system.service.IClUserService;
import com.cl.code.system.pojo.vo.UserSave;
import com.cl.code.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chengliang
 * @since 2022/11/9 0:38
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private IClUserService iClUserService;

    @Override
    public Long userAdd(UserSave userSave) {
        ClUser clUser = new ClUser();
        clUser.setUsername(userSave.getUsername());
        clUser.setPassword(userSave.getPassword());
        clUser.setNikeName(userSave.getNikeName());
        clUser.setPhone(userSave.getPhone());
        clUser.setEmail(userSave.getEmail());
        iClUserService.save(clUser);
        return clUser.getId();
    }

    @Override
    public boolean userEdit(UserSave userSave) {
        return true;
    }

}
