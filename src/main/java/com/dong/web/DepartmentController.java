package com.dong.web;

import com.dong.domain.Department;
import com.dong.service.DepartmentService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dongly on 2019/7/20
 */

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // 查询所有部门 编辑和添加员工时使用
    @RequestMapping("departList")
    @ResponseBody
    @RequiresPermissions(value = {"employee:add", "employee:edit"}, logical = Logical.OR)
    public List<Department> departList() {
        return departmentService.getDepartment();
    }
}
