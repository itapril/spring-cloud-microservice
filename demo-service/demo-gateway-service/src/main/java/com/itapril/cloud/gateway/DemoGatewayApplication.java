package com.itapril.cloud.gateway;

import com.itapril.cloud.gateway.filter.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Created by itapril on 2018/5/29 15:12.
 */
@EnableZuulProxy
@SpringCloudApplication
@EnableHystrixDashboard
public class DemoGatewayApplication {
    public static void main(String[] args){
        new SpringApplicationBuilder(DemoGatewayApplication.class).web(true).run(args);
    }



    @Bean
    public TestPreFilter01 testPreFilter01(){
        return new TestPreFilter01();
    }
    @Bean
    public TestPreFilter02 testPreFilter02(){
        return new TestPreFilter02();
    }
    @Bean
    public TestPostFilter testPostFilter(){
        return new TestPostFilter();
    }
    @Bean
    public TestErrorFilter testErrorFilter(){
        return new TestErrorFilter();
    }
    @Bean
    public TestRouteFilter testRouteFilter(){
        return new TestRouteFilter();
    }

}
