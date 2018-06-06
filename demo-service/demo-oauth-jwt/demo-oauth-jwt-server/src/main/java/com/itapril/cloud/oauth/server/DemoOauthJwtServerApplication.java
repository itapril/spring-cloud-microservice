package com.itapril.cloud.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by itapril on 2018/6/5 14:10.
 */
@EnableEurekaClient
@SpringBootApplication
@RestController
public class DemoOauthJwtServerApplication {

    public static void main(String[] args){
        SpringApplication.run(DemoOauthJwtServerApplication.class, args);
    }

}
