package com.dong.service;

import com.dong.domain.Employee;
import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;

import java.util.List;

/**
 * Created by dongly on 2019/7/19
 */

public interface EmployeeService {
    // 查询员工
    public PageListRes getEmployee(QueryVo vo);

    // 添加员工
    void addEmployee(Employee employee);

    // 更新员工信息
    void updateEmployee(Employee employee);

    // 更新员工离职
    void updateState(Long id);

    // 根据用户名查询
    Employee getEmployeeWithUsername(String username);

    // 根据 employee id 查 role num
    List<String> getRolesByEid(Long eid);

    // 查询 permission
    List<String> getPermissionsByEid(Long id);
}
