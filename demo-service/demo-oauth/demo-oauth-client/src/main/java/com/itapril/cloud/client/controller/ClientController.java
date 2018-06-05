package com.itapril.cloud.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by itapril on 2018/6/4 10:02.
 */
@RestController
public class ClientController {

    @GetMapping("/getClientInfo")
    public String getClientInfo(Principal principal){
        return"SUCCESS,授权成功" + principal.getName();
    }

}
