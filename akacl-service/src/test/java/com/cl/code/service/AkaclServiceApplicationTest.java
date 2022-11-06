package com.cl.code.service;

import com.cl.code.common.constant.EnableEnum;
import com.cl.code.module.system.entity.ClUser;
import com.cl.code.module.system.service.IClUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author chengliang
 * @since 2022/11/6 0:51
 */
@SpringBootTest
public class AkaclServiceApplicationTest {

    @Resource
    private IClUserService iClUserService;

    @Test
    public void test() {
        ClUser clUser = new ClUser();
        clUser.setUsername("admin");
        clUser.setPassword("");
        clUser.setNikeName("系统管理员");
        clUser.setPhone("15079292031");
        clUser.setEmail("714552682@qq.com");
        clUser.setCreateUserId(1L);
        clUser.setEnable(EnableEnum.DISABLE.getType());
        clUser.setUpdateUserId(1L);
        if (iClUserService.save(clUser)) {
            System.out.println("save success!!! id:" + clUser.getId());
        }
        if (iClUserService.removeById(1L)) {
            System.out.println("remove success!!!");
        }

    }

}
