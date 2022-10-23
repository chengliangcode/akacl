package com.cl.cocde.gateway.log;

import org.springframework.context.ApplicationEvent;

/**
 * @author chengliang
 * @since 2022/10/23 11:22
 */
public class GatewayLogEvent extends ApplicationEvent {

    public GatewayLogEvent(GatewayLog gatewayLog) {
        super(gatewayLog);
    }

    @Override
    public GatewayLog getSource() {
        return (GatewayLog) super.getSource();
    }
}
