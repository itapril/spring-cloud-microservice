package com.itapril.cloud.provider.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by itapril on 2018/5/28 16:25.
 */
@RestController
public class HelloController {
    @Value("${server.port}")
    String port;
    @RequestMapping("hello")
    public String hello(@RequestParam String name, @RequestParam String port){
        return "Hello World " + name +", i am from port: " + port;

    }
}
