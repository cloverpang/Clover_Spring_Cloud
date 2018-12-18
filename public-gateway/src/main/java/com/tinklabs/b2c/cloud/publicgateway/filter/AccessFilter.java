package com.tinklabs.b2c.cloud.publicgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
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

        System.out.println("send "+request.getMethod()+" request to " + request.getRequestURL().toString());

//        Object accessToken = request.getParameter("accessToken");
//        if(accessToken == null || "".equals(accessToken)) {
//            System.out.println("access token is empty");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseBody("{\"result\":\"token is not correct!\"}");// 返回错误内容
//            ctx.setResponseStatusCode(401);
//            return null;
//        }
//        System.out.println("access token ok");
        return null;
    }

}
