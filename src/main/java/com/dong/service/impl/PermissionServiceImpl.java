package com.dong.service.impl;

import com.dong.domain.Permission;
import com.dong.mapper.PermissionMapper;
import com.dong.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongly on 2019/7/21
 */

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissions() {
        List<Permission> permissions = permissionMapper.selectAll();

        return permissions;
    }

    @Override
    public List<Permission> getPermissionByRid(Long rid) {
        List<Permission> permissions = permissionMapper.selectPermissionByRid(rid);
        return permissions;
    }
}
