package com.dong.service;

import com.dong.domain.AjaxRes;
import com.dong.domain.Department;
import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;

import java.util.List;

/**
 * Created by dongly on 2019/7/20
 */
public interface DepartmentService {
    // 查找全部
    List<Department> getDepartment();

    // 分页查询
    PageListRes getDepartmentList(QueryVo vo);

    // 添加
    AjaxRes addDepartment(Department department);

    // 更新
    AjaxRes updateDepartment(Department department);

    // 删除
    AjaxRes deleteDepartment(Long id);
}
