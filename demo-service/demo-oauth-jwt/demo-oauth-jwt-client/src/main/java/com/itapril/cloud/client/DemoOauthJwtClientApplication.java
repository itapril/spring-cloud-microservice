package com.itapril.cloud.client;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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

    @GetMapping("user1")
    public Object user1(Authentication authentication){
        return authentication;
    }

    @GetMapping("user2")
    public Object user2(Principal principal){
        return principal;
    }


}
