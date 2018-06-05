package com.itapril.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by itapril on 2018/5/30 9:40.
 */
public class TestPreFilter01 extends ZuulFilter{

    /**
     *
     * @return
    pre：可以在请求被路由之前调用
    route：在路由请求时候被调用
    post：在route和error过滤器之后被调用
    error：处理请求时发生错误时被调用
     */
    /**
     * 过滤器类型
     * 顺序: pre ->routing -> post ,以上3个顺序出现异常时都可以触发error类型的filter
     */
    @Override
    public String filterType() {
//        return FilterConstants.PRE_TYPE ;
        return "pre" ;
    }

    @Override
    public int filterOrder() {
        return 0; //优先级为0 数字越大  优先级越高
    }

    @Override
    public boolean shouldFilter() {
        return true; //是否执行过该过滤器    true   说明需要
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        System.out.println(String.format("%s TestPreFilter01 request to %s", method, requestUri));
        // 这里的参数对应  provider  中的Controller --> hello
        String name = request.getParameter("name");
        String port = request.getParameter("port");

        Integer test = 1/0;
        System.out.println(test);

        if("itapril".equals(name)){
            ctx.setSendZuulResponse(true);   // 会进行路由  也就是会调用api服务提供者
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess",true);       //固定值放到ctx中  便于后面的filter获取使用
        }else{
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.set("isSuccess",false);       //固定值放到ctx中  便于后面的filter获取使用
            //返回内容给客户端
            ctx.setResponseBody("{\"result\":\"name auth not correct!\"}");// 返回错误内容
        }
        return null;
    }
}
