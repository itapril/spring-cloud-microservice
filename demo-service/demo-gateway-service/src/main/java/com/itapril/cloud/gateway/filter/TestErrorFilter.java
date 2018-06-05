package com.itapril.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by itapril on 2018/5/30 10:10.
 */
public class TestErrorFilter extends ZuulFilter{
    @Override
    public String filterType() {
//        return FilterConstants.ERROR_TYPE;
        return "error";
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
        System.out.println(String.format("%s TestErrorFilter request to %s", method, requestUri));
        // 这里的参数对应  provider  中的Controller --> hello
        String name = request.getParameter("name");
        String port = request.getParameter("port");
        System.out.println(String.format("the name is: %s; the post is: %s", name, port));

//        ctx.setSendZuulResponse(true);   // 会进行路由  也就是会调用api服务提供者
//        ctx.setResponseStatusCode(200);
//        ctx.set("isSuccess",true);

        /*ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(500);
        ctx.set("isSuccess",false);       //固定值放到ctx中  便于后面的filter获取使用
        //返回内容给客户端
        ctx.setResponseBody("{\"result\":\"系统内部错误!\"}");// 返回错误内容*/

        return null;
    }
}
