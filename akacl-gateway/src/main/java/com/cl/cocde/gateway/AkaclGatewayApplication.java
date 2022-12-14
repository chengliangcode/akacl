package com.cl.cocde.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chengliang
 * @since 2022/10/22 1:38
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AkaclGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkaclGatewayApplication.class, args);
    }

}
