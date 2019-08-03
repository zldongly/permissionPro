package com.dong.web;

import com.dong.domain.AjaxRes;
import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;
import com.dong.domain.Role;
import com.dong.service.RoleService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dongly on 2019/7/19
 */

@Controller
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;

    @RequestMapping("/role")
    @RequiresPermissions("role:index")
    public String role() {
        return "role";
    }

    // 查看所有 分页展示
    @RequestMapping("/getRoles")
    @ResponseBody
    @RequiresPermissions("role:index")
    public PageListRes getRoles(QueryVo vo) {
        System.out.println(vo);
        PageListRes roles = roleService.getRoles(vo);
        return roles;
    }


    // 查询所有role 添加编辑员工时使用
    @RequestMapping("roleList")
    @ResponseBody
    @RequiresPermissions(value = {"employee:add", "employee:edit"}, logical = Logical.OR)   // 两个权限中的其中一个
    public List<Role> roleList() {
        return roleService.roleList();
    }

    // 根据员工查询角色 编辑员工是使用
    @RequestMapping("getRoleByEid")
    @ResponseBody
    @RequiresPermissions("employee:edit")
    public List<Long> getRoleByEid(Long eid) {
        return roleService.getRoleByEid(eid);
    }


    // 添加
    @RequestMapping("/addRole")
    @ResponseBody
    @RequiresPermissions("role:index")
    public AjaxRes addRole(Role role) {
        System.out.println(role);
        AjaxRes res = new AjaxRes();
        try {
            roleService.addRole(role);
            res.setSuccess(true);
            res.setMsg("添加成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg("添加失败");
        }
        return res;
    }

    // 更新
    @RequestMapping("/updateRole")
    @ResponseBody
    @RequiresPermissions("role:index")
    public AjaxRes updateRole(Role role) {
        AjaxRes res = new AjaxRes();
        try {
            roleService.updateRole(role);
            res.setSuccess(true);
            res.setMsg("更新成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg("更新失败");
        }
        return res;
    }

    // 删除
    @RequestMapping("/deleteRole")
    @ResponseBody
    @RequiresPermissions("role:index")
    public AjaxRes deleteRole(Long rid) {
        AjaxRes res = new AjaxRes();
        try {
            roleService.deleteRole(rid);
            res.setSuccess(true);
            res.setMsg("删除成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg("删除失败");
        }
        return res;
    }


}
