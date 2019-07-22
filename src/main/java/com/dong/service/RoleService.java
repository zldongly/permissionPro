package com.dong.service;

import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;
import com.dong.domain.Role;

import java.util.List;

/**
 * Created by dongly on 2019/7/21
 */
public interface RoleService {
    // 查询角色 分页
    public PageListRes getRoles(QueryVo vo);

    // 查询所有角色
    public List<Role> roleList();

    // 添加角色
    public void addRole(Role role);

    // 更新角色 及其 权限
    public void updateRole(Role role);

    // 删除角色 及其权限
    void deleteRole(Long rid);

    // 根据 employ id 查询 角色
    List<Long> getRoleByEid(Long eid);
}
