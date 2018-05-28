package com.itapril.cloud.feign.web;

import com.itapril.cloud.feign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by itapril on 2018/5/28 17:03.
 */
@RestController
public class FeignController {
    @Autowired
    FeignService feignService;

    @Value("${server.port}")
    String port;

    @RequestMapping(value = "feign", method = RequestMethod.GET)
    public String helloWorld(@RequestParam String name){
        return feignService.helloWorld(name,port);
    }
}
