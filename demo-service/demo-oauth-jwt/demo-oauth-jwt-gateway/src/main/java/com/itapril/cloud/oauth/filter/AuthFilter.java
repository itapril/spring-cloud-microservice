package com.itapril.cloud.oauth.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by itapril on 2018/6/5 14:04.
 */
@Component
public class AuthFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("----------> " );
        Authentication authorization = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("----------> " + authorization);
        return null;
    }
}
