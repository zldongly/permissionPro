package com.dong.service.impl;

import com.dong.domain.PageListRes;
import com.dong.domain.Permission;
import com.dong.domain.QueryVo;
import com.dong.domain.Role;
import com.dong.mapper.RoleMapper;
import com.dong.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dongly on 2019/7/21
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageListRes getRoles(QueryVo vo) {
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Role> roles = roleMapper.selectAll();

        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(roles);

        return pageListRes;
    }

    @Override
    public List<Role> roleList() {
        return roleMapper.selectAll();
    }

    @Override
    public void addRole(Role role) {
        // 添加 role
        roleMapper.insert(role);    // 获取role id

        // 添加 role 的权限  角色权限关系
        for (Permission permission : role.getPermissions()) {
            roleMapper.insertRoleAndPermissionRel(role.getRid(), permission.getPid());
        }
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);    // 更新 角色编号、名称

        roleMapper.deletePermissionRel(role.getRid());      // 删除原权限关系

        // 重新添加 权限关系
        for (Permission permission : role.getPermissions()) {
            roleMapper.insertRoleAndPermissionRel(role.getRid(), permission.getPid());
        }
    }

    @Override
    public void deleteRole(Long rid) {

        roleMapper.deletePermissionRel(rid);        // first 删除关联的权限

        roleMapper.deleteByPrimaryKey(rid);         // 删除角色
    }

    @Override
    public List<Long> getRoleByEid(Long eid) {
        return roleMapper.getRoleByEid(eid);
    }
}
