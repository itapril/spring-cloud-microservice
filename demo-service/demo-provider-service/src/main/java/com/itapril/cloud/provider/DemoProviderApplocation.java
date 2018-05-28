package com.itapril.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by itapril on 2018/5/28 16:19.
 */
@SpringBootApplication
@EnableEurekaClient
public class DemoProviderApplocation {
    public static void main(String[] args){
        SpringApplication.run(DemoProviderApplocation.class, args);
    }
}
