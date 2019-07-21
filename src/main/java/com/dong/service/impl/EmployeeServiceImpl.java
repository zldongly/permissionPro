package com.dong.service.impl;

import com.dong.domain.Employee;
import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;
import com.dong.mapper.EmployeeMapper;
import com.dong.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dongly on 2019/7/19
 */

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    // 查找所有员工
    @Override
    public PageListRes getEmployee(QueryVo vo) {

        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Employee> employees = employeeMapper.selectAll();

        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(employees);

        System.out.println("service" + pageListRes);

        return pageListRes;
    }

    // 添加员工
    @Override
    public void saveEmployee(Employee employee) {
        employeeMapper.insert(employee);
    }

    // 更新
    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateByPrimaryKey(employee);
    }

    // 离职
    @Override
    public void updateState(Long id) {
        employeeMapper.updateState(id);
    }

}
