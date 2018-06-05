package com.itapril.cloud.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Created by itapril on 2018/6/4 10:02.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
public class DemoOauthClientApplication {

    public static void main(String[] args){
        SpringApplication.run(DemoOauthClientApplication.class, args);
    }

    @Bean
    LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalancerClient){
        return new LoadBalancerInterceptor(loadBalancerClient);
    }

}
