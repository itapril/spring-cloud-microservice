package com.itapril.cloud.oauth.server.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by itapril on 2018/6/6 17:13.
 */
@Component
public class WebAuthenticationEntryPoint implements AuthenticationEntryPoint {


    // 当访问的资源没有权限，会调用这里
    //这个 设置只对当前的Controller 起作用   比如当访问 http://localhost:7766/user1 会返回
    /**
     {
     "timestamp": 1528356178801,
     "status": 401,
     "error": "Unauthorized",
     "message": "认证失败",
     "path": "/user1"
     }
     * */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        // 如果登录失败，返回UNAUTHORIZED 这里可以自定义
        System.out.println("-----");
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"认证失败");
    }
}
