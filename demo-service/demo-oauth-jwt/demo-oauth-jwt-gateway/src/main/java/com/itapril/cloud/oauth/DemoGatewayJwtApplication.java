package com.itapril.cloud.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by itapril on 2018/6/5 13:54.
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class DemoGatewayJwtApplication {

    public static void main(String[] args){
        SpringApplication.run(DemoGatewayJwtApplication.class, args);
    }

}
