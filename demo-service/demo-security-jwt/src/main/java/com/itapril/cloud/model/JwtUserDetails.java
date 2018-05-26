package com.itapril.cloud.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by itapril on 2018/5/23 8:41.
 */
public class JwtUserDetails implements UserDetails{

    private String userName;
    private String token;
    private Long id;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(String userName, String token, Long id, List<GrantedAuthority> authorities) {
        this.userName = userName;
        this.token = token;
        this.id = id;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
//        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
//        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
//        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
//        return false;
    }
}
