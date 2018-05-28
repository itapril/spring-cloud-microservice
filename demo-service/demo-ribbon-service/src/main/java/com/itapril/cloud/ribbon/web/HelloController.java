package com.itapril.cloud.ribbon.web;

import com.itapril.cloud.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by itapril on 2018/5/28 16:05.
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @RequestMapping(value = "hi")
    public String hi(@RequestParam String name){
        return helloService.helloService(name);
    }
}
