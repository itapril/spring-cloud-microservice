package com.itapril.cloud.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by itapril on 2018/6/4 8:53.
 */
@SpringBootApplication
@EnableZuulProxy
public class DemoGatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(DemoGatewayApplication.class, args);
    }
}
