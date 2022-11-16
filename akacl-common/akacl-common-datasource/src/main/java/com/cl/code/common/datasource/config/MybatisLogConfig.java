package com.cl.code.common.datasource.config;

import com.cl.code.common.datasource.log.DaoLogImpl;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * mybatis log 配置
 *
 * @author chengliang
 * @since 2022/11/16 10:01
 */
public class MybatisLogConfig implements ApplicationListener<ApplicationStartingEvent> {

    /**
     * 替换mybatis的日志实现为logback
     *
     * @param event 事件
     */
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        LogFactory.useCustomLogging(DaoLogImpl.class);
    }

}
