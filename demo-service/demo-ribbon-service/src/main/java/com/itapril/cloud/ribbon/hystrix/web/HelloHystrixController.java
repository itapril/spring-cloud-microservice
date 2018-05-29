package com.itapril.cloud.ribbon.hystrix.web;

import com.itapril.cloud.ribbon.hystrix.service.HelloHystrixService;
import com.itapril.cloud.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by itapril on 2018/5/29 10:49.
 */
@RestController
public class HelloHystrixController {
    @Autowired
    HelloHystrixService helloHystrixService;
    @RequestMapping(value = "helloHystrix")
    public String hi(@RequestParam String name){
        return helloHystrixService.helloHystrixService(name);
    }
}
