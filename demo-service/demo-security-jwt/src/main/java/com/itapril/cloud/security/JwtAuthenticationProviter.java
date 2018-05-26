package com.itapril.cloud.security;

import com.itapril.cloud.model.JwtAuthenticationToken;
import com.itapril.cloud.model.JwtUser;
import com.itapril.cloud.model.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by itapril on 2018/5/23 11:34.
 */
@Component
public class JwtAuthenticationProviter extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtValidator validator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
        String token = jwtAuthenticationToken.getToken();
        JwtUser jwtUser = validator.validate(token);
        if(jwtUser == null){
            throw new RuntimeException("JWT Token is incorrect");
        }
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(jwtUser.getRole());
        return new JwtUserDetails(jwtUser.getUserName(), token, jwtUser.getId(), grantedAuthorities);
    }
}
