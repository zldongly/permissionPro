package com.dong.interceptor;

import com.dong.util.RequestUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dongly on 2019/7/25
 *
 * 拦截器
 */

public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("拦截器");
        RequestUtil.setReques(request);
        return true;
    }
}
