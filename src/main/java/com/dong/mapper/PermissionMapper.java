package com.dong.mapper;

import com.dong.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long pid);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    // 根据 role id 查询权限
    List<Permission> selectPermissionByRid(@Param("rid") Long rid);
}