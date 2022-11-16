package com.cl.cocde.gateway.log;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 日志时间监听
 *
 * @author chengliang
 * @since 2022/10/23 11:29
 */
@Slf4j(topic = "WEB_LOG")
@Component
public class GatewayLogListener implements ApplicationListener<GatewayLogEvent> {

    @Override
    public void onApplicationEvent(GatewayLogEvent event) {
        log.info(JSON.toJSONString(event.getSource(), JSONWriter.Feature.PrettyFormat));
    }

}
