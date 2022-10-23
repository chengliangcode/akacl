package com.cl.code.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chengliang
 * @since 2022/10/22 16:36
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AkaclServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkaclServiceApplication.class, args);
    }

}
