package com.itapril.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by itapril on 2018/5/30 10:25.
 */
public class TestRouteFilter extends ZuulFilter{

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        // 当 type 为 pre 中的filter异常时，  不会执行到这里
        System.out.println(String.format("%s TestRouteFilter request to %s", method, requestUri));

        return null;
    }
}
