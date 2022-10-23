package com.cl.cocde.gateway.log;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 日志时间监听
 *
 * @author chengliang
 * @since 2022/10/23 11:29
 */
@Component
public class GatewayLogListener implements ApplicationListener<GatewayLogEvent> {

    @Override
    public void onApplicationEvent(GatewayLogEvent event) {
        System.out.println(event.getSource());
    }

}
