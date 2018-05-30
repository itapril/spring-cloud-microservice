package com.itapril.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by itapril on 2018/5/30 10:07.
 */
public class TestPostFilter extends ZuulFilter{

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
//        return RequestContext.getCurrentContext().getBoolean("isSuccess");
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        System.out.println(String.format("%s TestPostFilter request to %s", method, requestUri));
        // 这里的参数对应  provider  中的Controller --> hello
        String name = request.getParameter("name");
        String port = request.getParameter("port");
        System.out.println(String.format("the name is: %s; the post is: %s", name, port));

        return null;
    }
}
