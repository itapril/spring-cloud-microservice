package com.itapril.cloud.security;

import com.itapril.cloud.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

/**
 * Created by itapril on 2018/5/23 9:49.
 */
@Component
public class JwtGenerator {

    public String generate(JwtUser jwtUser){
        Claims claims = Jwts.claims().setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role",jwtUser.getRole());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512,"itapril").compact();
    }

}
