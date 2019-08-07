package com.dong.web;

import com.dong.domain.AjaxRes;
import com.dong.domain.Employee;
import com.dong.service.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;

/**
 * Created by dongly on 2019/8/6
 */

@Controller
public class ProfileController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("profile")
    public ModelAndView employee() {
        ModelAndView modelAndView = new ModelAndView();
        Employee employee = employeeService.myProfile();
        String dateString = "";
        if (employee.getInputTime() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateString = formatter.format(employee.getInputTime());
        }

        modelAndView.addObject("employee", employee);
        modelAndView.addObject("inputDate", dateString);
        modelAndView.setViewName("profile");

        return modelAndView;
    }

    @RequestMapping("updateProfile")
    @ResponseBody
    public AjaxRes updateProfile(Employee employee) {
        return employeeService.updateProfile(employee);
    }

    @RequestMapping("updatePwd")
    @ResponseBody
    public AjaxRes updatePwd (String newPassword) {
        return employeeService.updatePassword(newPassword);
    }
}
