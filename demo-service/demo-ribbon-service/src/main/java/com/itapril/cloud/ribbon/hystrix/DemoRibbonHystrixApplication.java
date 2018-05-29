package com.itapril.cloud.ribbon.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by itapril on 2018/5/29 10:35.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class DemoRibbonHystrixApplication {
/*    // 打包报错   注释掉方便打包    如果需要测试 单独打开
    public static void main(String[] args){
        SpringApplication.run(DemoRibbonHystrixApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }*/
}
