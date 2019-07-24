package com.dong.web.filter;

import com.dong.domain.AjaxRes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by dongly on 2019/7/24
 */

public class MyFormFilter extends FormAuthenticationFilter {
    // 认证成功时
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("Login Success");
        response.setCharacterEncoding("utf-8");
        AjaxRes ajaxRes = new AjaxRes();
        ajaxRes.setSuccess(true);
        ajaxRes.setMsg("登录成功");
        String jsonString = new ObjectMapper().writeValueAsString(ajaxRes);

        response.getWriter().println(jsonString);

        return false;
    }

    // 认证失败时
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        System.out.println("Login Failure");
        response.setCharacterEncoding("utf-8");

        AjaxRes ajaxRes = new AjaxRes();
        ajaxRes.setSuccess(false);
        if (e != null) {
            String name = e.getClass().getName();
            if (name.equals(UnknownAccountException.class.getName())) {
                // 用户名不存在
                ajaxRes.setMsg("用户名不存在");
            } else if (name.equals(IncorrectCredentialsException.class.getName())) {
                // 密码错误
                ajaxRes.setMsg("密码不正确");
            }
        } else {
            // 异常
            ajaxRes.setMsg("登录异常");
        }
        try {
            response.getWriter().println(new ObjectMapper().writeValueAsString(ajaxRes));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
