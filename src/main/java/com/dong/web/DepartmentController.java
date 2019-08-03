package com.dong.web;

import com.dong.domain.AjaxRes;
import com.dong.domain.Department;
import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;
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

    @RequestMapping("department")
    public String department() {
        return "department";
    }

    // 查询所有部门 编辑和添加员工时使用
    @RequestMapping("departList")
    @ResponseBody
    @RequiresPermissions(value = {"employee:add", "employee:edit"}, logical = Logical.OR)
    public List<Department> departList() {
        return departmentService.getDepartment();
    }

    // 分页 查询部门列表
    @RequestMapping("departmentList")
    @ResponseBody
    @RequiresPermissions("department:index")
    public PageListRes departmentList (QueryVo vo) {
        return departmentService.getDepartmentList(vo);
    }

    // 添加
    @RequestMapping("addDep")
    @ResponseBody
    @RequiresPermissions("department:index")
    public AjaxRes addDep(Department department) {
        return departmentService.addDepartment(department);
    }

    // 更新
    @RequestMapping("updateDep")
    @ResponseBody
    @RequiresPermissions("department:index")
    public AjaxRes updateDep(Department department) {
        return departmentService.updateDepartment(department);
    }

    // 删除
    @RequestMapping("deleteDep")
    @ResponseBody
    @RequiresPermissions("department:index")
    public AjaxRes deleteDep(Long id) {
        return departmentService.deleteDepartment(id);
    }


}
