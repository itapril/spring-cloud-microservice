package com.itapril.cloud.oauth.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by itapril on 2018/6/4 9:26.
 */
@RestController
public class UserController {

    @RequestMapping("user")
    public Principal user(Principal user){
        return user;
    }

}
