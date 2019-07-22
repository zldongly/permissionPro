package com.dong.web;

import com.dong.domain.Permission;
import com.dong.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dongly on 2019/7/21
 */

@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    // 查找所有权限
    @RequestMapping("getPermission")
    @ResponseBody
    public List<Permission> getPermission() {
        List<Permission> permissions = permissionService.getPermissions();
        return permissions;
    }

    // role id 查看对应权限
    @RequestMapping("getPermissionByRid")
    @ResponseBody
    public List<Permission> getPermissionByRid(Long rid) {
        System.out.println(rid);
        List<Permission> permissions = permissionService.getPermissionByRid(rid);
        return permissions;
    }

}
