package com.itapril.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by itapril on 2018/5/30 9:48.
 */
public class TestPreFilter02 extends ZuulFilter{


    @Override
    public String filterType() {
//        return FilterConstants.PRE_TYPE;
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        // 上一个filter设置的值
//        return RequestContext.getCurrentContext().getBoolean("isSuccess");
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        System.out.println(String.format("%s TestPreFilter02 request to %s", method, requestUri));

        String name = request.getParameter("name");
        String port = request.getParameter("port");
        if("18806".equals(port)){
            ctx.setSendZuulResponse(true);   // 会进行路由  也就是会调用api服务提供者
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess",true);       //固定值放到ctx中  便于后面的filter获取使用
        }else{
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.set("isSuccess",false);       //固定值放到ctx中  便于后面的filter获取使用
            //返回内容给客户端
            ctx.setResponseBody("{\"result\":\"port auth not correct!\"}");// 返回错误内容
        }
        return null;
    }
}
