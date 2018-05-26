package com.itapril.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by itapril on 2018/5/23 10:54.
 */
@RestController
@RequestMapping("/rest/hello")
public class HelloController {

    @GetMapping
    public String hello(){
        return "Hello World ~~~";
    }

}
