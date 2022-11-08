package com.cl.code.system.service;

import com.cl.code.system.pojo.vo.UserSave;

/**
 * @author chengliang
 * @since 2022/11/9 0:37
 */
public interface UserService {

    Long userAdd(UserSave userSave);

    boolean userEdit(UserSave userSave);

}
