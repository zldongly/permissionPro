package com.dong.service;

import com.dong.domain.Employee;
import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;

/**
 * Created by dongly on 2019/7/19
 */

public interface EmployeeService {
    // 查询员工
    public PageListRes getEmployee(QueryVo vo);

    // 添加员工
    void saveEmployee(Employee employee);

    // 更新员工信息
    void updateEmployee(Employee employee);

    // 更新员工离职
    void updateState(Long id);
}
