package com.itapril.cloud.oauth;

import com.itapril.cloud.oauth.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

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

    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }

}
