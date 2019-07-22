package com.dong.service;

import com.dong.domain.Permission;

import java.util.List;

/**
 * Created by dongly on 2019/7/21
 */
public interface PermissionService {
    // 查找所有权限
    public List<Permission> getPermissions();

    List<Permission> getPermissionByRid(Long rid);
}
