package com.itapril.cloud.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by itapril on 2018/5/25 14:20.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("--------------------");
        return null;
    }
}
