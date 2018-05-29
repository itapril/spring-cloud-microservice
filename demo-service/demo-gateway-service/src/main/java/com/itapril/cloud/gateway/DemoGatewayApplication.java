package com.itapril.cloud.gateway;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by itapril on 2018/5/29 15:12.
 */
@EnableZuulProxy
@SpringCloudApplication
public class DemoGatewayApplication {
    public static void main(String[] args){
        new SpringApplicationBuilder(DemoGatewayApplication.class).web(true).run(args);
    }
}
