package com.itapril.cloud.ribbon.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by itapril on 2018/5/29 10:48.
 */
@Service
public class HelloHystrixService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${server.port}")
    String port;

    //第一次请求的时候   也会去调  helloHystrixError 原因还未找到
    // 处理： 设置 hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 5000
    // 当我们把provider服务给停了的  回去调用  helloHystrixError
    // 当再次把provider服务正常启动z之后  又能正常调用了
    @HystrixCommand(fallbackMethod = "helloHystrixError")
    public String helloHystrixService(String name){
        // 第一次请求访问的时候   会打印出 String.class， 第二次再请求是  不再打印
        return restTemplate.getForObject("http://demo-provider-service/hello?name="+name+"&port="+port, String.class);
    }

    public String helloHystrixError(String name){
        return "ribbon name: " + name +"; 发生错误了";
    }

}
