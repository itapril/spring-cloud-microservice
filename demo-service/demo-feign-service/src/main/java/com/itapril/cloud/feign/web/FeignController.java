package com.itapril.cloud.feign.web;

import com.itapril.cloud.feign.entity.User;
import com.itapril.cloud.feign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "getUser", method = RequestMethod.GET)
    public User getUser(User user){
        System.out.println("the user is " + user);
        return feignService.getUser(user);
    }
    @RequestMapping(value = "postUser", method = RequestMethod.POST)
    public User postUser(@RequestBody User user){
        System.out.println("the user is " + user);
        return feignService.postUser(user);
    }

    // 请求： localhost:8764/getSimple1?id=123
    @RequestMapping(value = "getSimple1", method = RequestMethod.GET)
    public String getSimple1(@RequestParam Long id){
        System.out.println("the id is " + id);
        return feignService.getSimple1(id);
    }

    //  请求： localhost:8764/getSimple1/123
    @GetMapping("getSimple1/{id}")// 同  @RequestMapping(value = "getSimple1/{id}", method = RequestMethod.GET)
    public String getSimple_1(@PathVariable Long id){
        System.out.println("the id is " + id);
        return feignService.getSimple1(id);
    }

    @RequestMapping(value = "getSimple2", method = RequestMethod.GET)
    public String getSimple2(@RequestParam Long id){
        System.out.println("the id is " + id);
        return feignService.getSimple2(id);
    }

}
