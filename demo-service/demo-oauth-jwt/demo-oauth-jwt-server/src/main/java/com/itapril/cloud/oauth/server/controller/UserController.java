package com.itapril.cloud.oauth.server.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by itapril on 2018/6/7 14:43.
 */
@RestController
public class UserController {

    @GetMapping("/user1")
    public Principal user1(Principal user){
        return user;
    }

    @GetMapping("user2")
    public Authentication user2(Authentication authentication){
        return authentication;
    }

}
