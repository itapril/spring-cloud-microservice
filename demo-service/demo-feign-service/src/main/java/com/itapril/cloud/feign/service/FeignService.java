package com.itapril.cloud.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by itapril on 2018/5/28 17:01.
 */
@FeignClient(value = "demo-provider-service")
public interface FeignService {
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    String helloWorld(@RequestParam(value = "name") String name,@RequestParam(value = "port") String port);
}
