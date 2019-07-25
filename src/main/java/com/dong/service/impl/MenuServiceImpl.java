package com.dong.service.impl;

import com.dong.domain.*;
import com.dong.mapper.MenuMapper;
import com.dong.service.MenuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by dongly on 2019/7/24
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public PageListRes getMenuList(QueryVo vo) {
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Menu> menus = menuMapper.selectAll();

        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(menus);

        return pageListRes;
    }

    @Override
    public List<Menu> parentList() {
        return menuMapper.selectAll();
    }

    @Override
    public void addMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public AjaxRes updateMenu(Menu menu) {
        AjaxRes res = new AjaxRes();

        // 不能设置自己的子菜单为父菜单
        if (menu.getParent() != null) {
            Long id = menu.getParent().getId();
            Long parentId = menuMapper.selectParentId(id);  // id的父菜单
            if (menu.getId().equals(parentId)) {
                res.setSuccess(false);
                res.setMsg("不能设置自己的子菜单为父菜单");
                return res;
            }
        }
        // 更新
        try {
            menuMapper.updateByPrimaryKey(menu);
            res.setSuccess(true);
            res.setMsg("更新成功");
        } catch (Exception e){
            res.setSuccess(false);
            res.setMsg("更新失败");
        }
        return res;
    }

    @Override
    public AjaxRes deleteMenu(Long id) {
        AjaxRes res = new AjaxRes();


        try {
            // 更新 取消以id为父菜单的菜单关系
            menuMapper.updateMenuRel(id);
            // 删除
            menuMapper.deleteByPrimaryKey(id);
            res.setSuccess(true);
            res.setMsg("删除成功");
        } catch (Exception e){
            res.setSuccess(false);
            res.setMsg("删除失败");
        }
        return res;
    }

    @Override
    public List<Menu> getTreeData() {
        List<Menu> treeData = menuMapper.getTreeData();
        // 判断用户权限
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();  // 当前用户
        if (!employee.getAdmin()) {
            checkPermission(treeData);
        }
        return treeData;
    }

    // 检测用户权限
    private void checkPermission(List<Menu> menus) {
        Subject subject = SecurityUtils.getSubject();
        // 遍历所有菜单
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            Menu menu = iterator.next();
            if (menu.getPermission() != null) {
                String presource = menu.getPermission().getPresource();
                if (!subject.isPermitted(presource)) {
                    iterator.remove();
                    continue;
                }
            }
            // 检测子菜单    递归
            if (menu.getChildren().size() > 0) {
                checkPermission(menu.getChildren());
            }
        }
    }
}
