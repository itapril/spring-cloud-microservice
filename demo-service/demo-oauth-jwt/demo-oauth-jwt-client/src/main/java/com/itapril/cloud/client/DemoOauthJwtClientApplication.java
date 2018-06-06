package com.itapril.cloud.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by itapril on 2018/6/5 13:53.
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class DemoOauthJwtClientApplication {

    public static void main(String[] args){
        SpringApplication.run(DemoOauthJwtClientApplication.class,args);
    }


    @GetMapping("test")
    public String test(){
        return "test success";
    }


}
