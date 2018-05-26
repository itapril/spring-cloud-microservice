package com.itapril.cloud.security;

import com.itapril.cloud.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by itapril on 2018/5/25 10:42.
 */
@Component
public class JwtTokenUtil implements Serializable{

    private static final long serialVersionUID = -3301605591108950415L;

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_AUDIENEC = "audience";
    static final String CLAIM_KEY_CREATED = "created";


    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    public String getUserNameFromToken(String token){
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            e.printStackTrace();
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token){
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long)claims.get(CLAIM_KEY_CREATED));
        }catch (Exception e){
            e.printStackTrace();
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token){
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        }catch (Exception e){
            e.printStackTrace();
            expiration = null;
        }
        return expiration;
    }

    public String getAudienceFromToken(String token){
        String audience;
        try {
            final Claims claims = getClaimsFromToken(token);
            audience = claims.getAudience();
        }catch (Exception e){
            e.printStackTrace();
            audience = null;
        }
        return audience;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        if(expiration != null){
            return expiration.before(new Date());
        }
        return false;
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public String refreshToken(String token){
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        }catch (Exception e){
            e.printStackTrace();
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        JwtUser user = (JwtUser) userDetails;
        final String username = getUserNameFromToken(token);
//        final Date created = getCreatedDateFromToken(token);
        return username.equals(user.getUserName()) && !isTokenExpired(token);
    }


}
