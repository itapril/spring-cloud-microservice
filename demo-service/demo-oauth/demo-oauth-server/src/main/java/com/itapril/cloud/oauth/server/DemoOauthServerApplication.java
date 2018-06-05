package com.itapril.cloud.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by itapril on 2018/6/4 9:06.
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableResourceServer
public class DemoOauthServerApplication {

    public static void main(String[] args){
        SpringApplication.run(DemoOauthServerApplication.class, args);
    }

}
