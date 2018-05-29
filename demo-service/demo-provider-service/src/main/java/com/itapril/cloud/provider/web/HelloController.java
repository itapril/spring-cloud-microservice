package com.itapril.cloud.provider.web;

import com.itapril.cloud.provider.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created by itapril on 2018/5/28 16:25.
 */
@RestController
public class HelloController {
    @Value("${server.port}")
    String port;
    @RequestMapping("hello")
    public String hello(@RequestParam String name, @RequestParam String port){
        return "Hello World " + name +", i am from port: " + port;
    }


    @GetMapping("getUser")
    public User getUser(User user){
        System.out.println("the user is " + user.toString());
        return user;

    }


    @PostMapping("postUser")
    public User postUser(@RequestBody  User user){
        System.out.println("the user is " + user.toString());
        return user;
    }

    @GetMapping("simple/{id}")
    public String getSimple(@PathVariable Long id){
        return "the id is " + id;
    }


}
