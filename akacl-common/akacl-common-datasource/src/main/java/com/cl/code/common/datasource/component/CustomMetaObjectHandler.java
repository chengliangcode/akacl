package com.cl.code.common.datasource.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cl.code.common.core.constant.DeleteEnum;
import com.cl.code.common.core.constant.EnableEnum;
import com.cl.code.common.core.util.IdGeneratorUtil;
import com.cl.code.common.datasource.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author chengliang
 * @since 2022/11/6 23:37
 */
@Slf4j
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, BaseEntity.ID, IdGeneratorUtil.generateId());
        this.fillStrategy(metaObject, BaseEntity.CREATE_TIME, System.currentTimeMillis());
        this.fillStrategy(metaObject, BaseEntity.UPDATE_TIME, System.currentTimeMillis());
        this.fillStrategy(metaObject, BaseEntity.ENABLE, EnableEnum.ENABLE.getType());
        this.fillStrategy(metaObject, BaseEntity.DELETED, DeleteEnum.UN_DELETE.getType());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, BaseEntity.UPDATE_TIME, System.currentTimeMillis());
    }

}
