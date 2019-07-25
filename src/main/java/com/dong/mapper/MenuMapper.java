package com.dong.mapper;

import com.dong.domain.Menu;
import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    // 查询父菜单 id
    Long selectParentId(Long id);

    // 取消以id为父菜单的菜单关系
    int updateMenuRel(Long id);

    // 获取树形菜单
    List<Menu> getTreeData();
}