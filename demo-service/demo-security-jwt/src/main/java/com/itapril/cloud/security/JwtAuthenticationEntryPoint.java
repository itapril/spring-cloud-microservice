package com.itapril.cloud.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by itapril on 2018/5/23 8:37.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{


    // 当访问的资源没有权限，会调用这里
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        // 如果登录失败，返回UNAUTHORIZED 这里可以自定义
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"UNAUTHORIZED");
    }
}
