package com.dong.web;

import com.dong.domain.Department;
import com.dong.service.DepartmentService;
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

    // 查询所有部门
    @RequestMapping("departList")
    @ResponseBody
    public List<Department> departList() {
        return departmentService.getDepartment();
    }
}
