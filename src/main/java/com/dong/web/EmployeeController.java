package com.dong.web;

import com.dong.domain.AjaxRes;
import com.dong.domain.Employee;
import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;
import com.dong.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dongly on 2019/7/19
 */

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employee")
    public String employee() {
        return "employee";
    }

    // 获取所有
    @RequestMapping("/employeeList")
    @ResponseBody
    public PageListRes employList(QueryVo vo) {
        PageListRes pageListRes = employeeService.getEmployee(vo);

        return pageListRes;
    }

    // 添加
    @RequestMapping("saveEmployee")
    @ResponseBody
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
}
