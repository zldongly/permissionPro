package com.dong.web;

import com.dong.domain.AjaxRes;
import com.dong.domain.Employee;
import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;
import com.dong.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dongly on 2019/7/19
 */

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employee")
    @RequiresPermissions("employee:index")
    public String employee() {
        return "employee";
    }

    // 获取所有
    @RequestMapping("/employeeList")
    @ResponseBody
    @RequiresPermissions("employee:index")
    public PageListRes employList(QueryVo vo) {
        System.out.println(vo);
        PageListRes pageListRes = employeeService.getEmployee(vo);

        return pageListRes;
    }

    // 添加
    @RequestMapping("saveEmployee")
    @ResponseBody
    @RequiresPermissions("employee:add")
    public AjaxRes saveEmployee(Employee employee) {
        AjaxRes res = new AjaxRes();
        try {
            employee.setState(true);
            employeeService.saveEmployee(employee);
            res.setSuccess(true);
            res.setMsg("保存成功");
        } catch (Exception e){
            res.setSuccess(false);
            res.setMsg("保存失败");
        }
        return res;
    }

    // 更新
    @RequestMapping("updateEmployee")
    @ResponseBody
    @RequiresPermissions("employee:edit")
    public AjaxRes updateEmployee(Employee employee) {
        System.out.println(employee);
        employee.setState(true);

        AjaxRes res = new AjaxRes();
        try {
            employeeService.updateEmployee(employee);
            res.setSuccess(true);
            res.setMsg("更新成功");
        } catch (Exception e){
            res.setSuccess(false);
            res.setMsg("更新失败");
        }
        return res;
    }

    // 离职更新
    @RequestMapping("updateState")
    @ResponseBody
    @RequiresPermissions("employee:delete")
    public AjaxRes updateState(Long id) {
        AjaxRes res = new AjaxRes();
        try {
            employeeService.updateState(id);
            res.setSuccess(true);
            res.setMsg("离职更新成功");
        } catch (Exception e){
            e.printStackTrace();
            res.setSuccess(false);
            res.setMsg("离职更新失败");
        }
        return res;
    }


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
