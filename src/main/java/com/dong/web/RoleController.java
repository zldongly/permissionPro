package com.dong.web;

import com.dong.domain.AjaxRes;
import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;
import com.dong.domain.Role;
import com.dong.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dongly on 2019/7/19
 */

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/role")
    public String role() {
        return "role";
    }

    // 查看所有
    @RequestMapping("/getRoles")
    @ResponseBody
    public PageListRes getRoles(QueryVo vo) {
        System.out.println(vo);
        PageListRes roles = roleService.getRoles(vo);
        return roles;
    }


    // 查看所有role
    @RequestMapping("roleList")
    @ResponseBody
    public List<Role> roleList() {
        return roleService.roleList();
    }

    @RequestMapping("getRoleByEid")
    @ResponseBody
    public List<Long> getRoleByEid(Long eid) {
        return roleService.getRoleByEid(eid);
    }


    // 添加
    @RequestMapping("/addRole")
    @ResponseBody
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
