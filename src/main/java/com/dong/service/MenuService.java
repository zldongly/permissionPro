package com.dong.service;

import com.dong.domain.AjaxRes;
import com.dong.domain.Menu;
import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;

import java.util.List;

/**
 * Created by dongly on 2019/7/24
 */

public interface MenuService {
    // 分页查询菜单
    public PageListRes getMenuList(QueryVo vo);

    // 查询所有菜单
    public List<Menu> parentList();

    // 添加
    public void addMenu(Menu menu);

    // 更新
    public AjaxRes updateMenu(Menu menu);

    // 删除
    public AjaxRes deleteMenu(Long id);

    // 获取树形菜单
    public List<Menu> getTreeData();

}
