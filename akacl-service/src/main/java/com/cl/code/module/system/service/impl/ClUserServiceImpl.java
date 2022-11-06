package com.cl.code.module.system.service.impl;

import com.cl.code.module.system.entity.ClUser;
import com.cl.code.module.system.mapper.ClUserMapper;
import com.cl.code.module.system.service.IClUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author chengliang
 * @since 2022-11-06
 */
@Service
public class ClUserServiceImpl extends ServiceImpl<ClUserMapper, ClUser> implements IClUserService {

}
