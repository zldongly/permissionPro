package com.dong.web;

import com.dong.domain.AjaxRes;
import com.dong.domain.Menu;
import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;
import com.dong.service.EmployeeService;
import com.dong.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dongly on 2019/7/19
 */

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu")
    @RequiresPermissions("menu:index")
    public String menu() {
        return "menu";
    }

    // 分页查询
    @RequestMapping("menuList")
    @ResponseBody
    @RequiresPermissions("menu:index")
    public PageListRes menuList(QueryVo vo) {
        PageListRes res =menuService.getMenuList(vo);
        return res;
    }

    // 查询所有 添加编辑菜单时使用
    @RequestMapping("/parentMenuList")
    @ResponseBody
    @RequiresPermissions("menu:index")
    public List<Menu> parentMenuList() {
        return menuService.parentList();
    }

    // 添加
    @RequestMapping("/addMenu")
    @ResponseBody
    @RequiresPermissions("menu:index")
    public AjaxRes addMenu(Menu menu) {
        AjaxRes res = new AjaxRes();
        try {
            menuService.addMenu(menu);
            res.setSuccess(true);
            res.setMsg("保存成功");
        } catch (Exception e){
            res.setSuccess(false);
            res.setMsg("保存失败");
        }
        return res;
    }

    // 更新
    @RequestMapping("editMenu")
    @ResponseBody
    @RequiresPermissions("menu:index")
    public AjaxRes editMenu(Menu menu) {
        return menuService.updateMenu(menu);
    }

    // 删除
    @RequiresPermissions("menu:index")
    @RequestMapping("deleteMenu")
    @ResponseBody
    public AjaxRes deleteMenu(Long id) {
        return menuService.deleteMenu(id);
    }

    // 获取树形菜单
    @RequestMapping("getTreeData")
    @ResponseBody
    public List<Menu> getTreeData() {
        return menuService.getTreeData();
    }
}
