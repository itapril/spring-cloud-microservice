package com.itapril.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.itapril.cloud.model.JwtUser;
import com.itapril.cloud.security.JwtGenerator;
import com.itapril.cloud.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by itapril on 2018/5/23 10:58.
 */
@RestController
@RequestMapping("token")
public class TokenController {

    private JwtGenerator jwtGenerator;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser){
        return jwtGenerator.generate(jwtUser);
    }

    @PostMapping(value = "create")
    public String createToken(@RequestBody final JwtUser jwtUser){

        String username = jwtUser.getUserName();
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        UserDetails userDetails = new UserDetails();

        final String CLAIM_KEY_USERNAME = "sub";
        final String CLAIM_KEY_CREATED = "created";

        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, username);
        claims.put(CLAIM_KEY_CREATED, new Date());
        String token = jwtTokenUtil.generateToken(claims);
        System.out.println(token);
        return token;
    }

    @PostMapping(value = "isTokenExpired")
    public Boolean isTokenExpired(@RequestBody String param){
        JSONObject paramObject = JSONObject.parseObject(param);
        String token = paramObject.getString("token");
        Boolean flag = jwtTokenUtil.isTokenExpired(token);
        System.out.println(flag);
        return flag;
    }

    @PostMapping(value = "getTokenDate")
    public JSONObject getTokenDate(@RequestBody String param){
        JSONObject paramObject = JSONObject.parseObject(param);
        String token = paramObject.getString("token");
        Date createTokenDate = jwtTokenUtil.getCreatedDateFromToken(token);
        Date expirationTokenDate = jwtTokenUtil.getExpirationDateFromToken(token);
        String refreshToken = jwtTokenUtil.refreshToken(token);
        paramObject.put("createTokenDate",createTokenDate);
        paramObject.put("expirationTokenDate",expirationTokenDate);
        paramObject.put("refreshToken",refreshToken);
        paramObject.put("token",token);
        System.out.println(paramObject);
        return paramObject;
    }



}
