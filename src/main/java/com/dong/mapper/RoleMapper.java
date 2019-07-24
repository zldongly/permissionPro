package com.dong.mapper;

import com.dong.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long rid);

    int insert(Role record);

    Role selectByPrimaryKey(Long rid);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    // 添加关系
    int insertRoleAndPermissionRel(@Param("rid") Long rid, @Param("pid") Long pid);

    int deletePermissionRel(Long rid);

    // 根据employ id 查角色rid  rel
    List<Long> getRoleByEid(Long eid);
}