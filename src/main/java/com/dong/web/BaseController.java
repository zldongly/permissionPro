package com.dong.web;

import com.dong.domain.AjaxRes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongly on 2019/8/3
 */
public class BaseController {

    // 没有权限处理
    @ExceptionHandler(AuthorizationException.class)
    public void handleShiroException(HandlerMethod method, HttpServletResponse response) throws IOException {
        // 判断请求方式
        ResponseBody methodAnnotation = method.getMethodAnnotation(ResponseBody.class); // 获取方法上的注解
        if (methodAnnotation != null) {
            AjaxRes ajaxRes = new AjaxRes();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("没有权限");
            String s = new ObjectMapper().writeValueAsString(ajaxRes);
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(s);
        } else {
            response.sendRedirect("noPermission.jsp");
        }
    }
}
