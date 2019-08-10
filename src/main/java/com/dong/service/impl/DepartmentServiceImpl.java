package com.dong.service.impl;

import com.dong.domain.AjaxRes;
import com.dong.domain.Department;
import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;
import com.dong.mapper.DepartmentMapper;
import com.dong.service.DepartmentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dongly on 2019/7/20
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    // 查询全部
    @Override
    public List<Department> getDepartment() {
        return departmentMapper.selectAll();
    }

    // 分页查询
    @Override
    public PageListRes getDepartmentList(QueryVo vo) {
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Department> departments = departmentMapper.selectAll();

        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(departments);

        return pageListRes;
    }

    // 添加
    @Override
    public AjaxRes addDepartment(Department department) {
        AjaxRes res = new AjaxRes();
        try {
            departmentMapper.insert(department);
            res.setSuccess(true);
            res.setMsg("添加成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg("添加失败");
        }
        return res;
    }

    // 更新
    @Override
    public AjaxRes updateDepartment(Department department) {
        AjaxRes res = new AjaxRes();
        try {
            departmentMapper.updateByPrimaryKey(department);
            res.setSuccess(true);
            res.setMsg("更新成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg("更新失败");
        }
        return res;
    }

    // 删除
    @Override
    public AjaxRes deleteDepartment(Long id) {
        AjaxRes res = new AjaxRes();
        try {
            departmentMapper.deleteEmployeeDepRel(id);
            departmentMapper.deleteByPrimaryKey(id);
            res.setSuccess(true);
            res.setMsg("删除成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg("删除失败");
        }
        return res;
    }

    @Override
    public Map getDepartMap() {
        Map map = new HashMap();
        List<Department> departments = departmentMapper.selectAll();
        for (Department department : departments) {
            map.put(department.getName(), department.getId());
        }
        return map;
    }

}
