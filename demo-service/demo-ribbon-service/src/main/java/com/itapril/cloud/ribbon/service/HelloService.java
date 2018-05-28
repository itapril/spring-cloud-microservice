package com.itapril.cloud.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by itapril on 2018/5/28 16:02.
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${server.port}")
    String port;

    public String helloService(String name){
        // 第一次请求访问的时候   会打印出 String.class， 第二次再请求是  不再打印
        return restTemplate.getForObject("http://demo-provider-service/hello?name="+name+"&port="+port, String.class);
    }
}
