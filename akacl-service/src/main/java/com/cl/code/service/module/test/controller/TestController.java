package com.cl.code.service.module.test.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author chengliang
 * @since 2022/10/22 16:38
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello ".concat(name).concat("!!");
    }

    @GetMapping("/hello/{name}")
    public String hello2(@PathVariable String name) {
        return "Hello ".concat(name).concat("!!");
    }

}
