package com.dong.service.impl;

import com.dong.domain.Employee;
import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;
import com.dong.domain.Role;
import com.dong.mapper.EmployeeMapper;
import com.dong.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.crypto.hash.Md5Hash;
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
        List<Employee> employees = employeeMapper.selectAll(vo);

        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(employees);

        return pageListRes;
    }

    // 添加员工
    @Override
    public void saveEmployee(Employee employee) {
        Md5Hash hash = new Md5Hash(employee.getPassword(), employee.getUsername(), 2);
        employee.setPassword(hash.toString());

        employeeMapper.insert(employee);

        for (Role role : employee.getRoles()) {
            employeeMapper.insertRoleRel(employee.getId(), role.getRid());
        }

    }

    // 更新
    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateByPrimaryKey(employee);    // 更新员工

        // 更新员工 角色
        employeeMapper.deleteRoleRel(employee.getId()); // 删除关系
        for (Role role : employee.getRoles()) {         // 重建关系
            employeeMapper.insertRoleRel(employee.getId(), role.getRid());
        }
    }

    // 离职
    @Override
    public void updateState(Long id) {
        employeeMapper.updateState(id);
    }

    @Override
    public Employee getEmployeeWithUsername(String username) {
        return employeeMapper.getEmployeeWithUsername(username);
    }

    @Override
    public List<String> getRolesByEid(Long eid) {
        return employeeMapper.getRolesByEid(eid);
    }

    @Override
    public List<String> getPermissionsByEid(Long eid) {
        return employeeMapper.getPermissionsByEid(eid);
    }

}
