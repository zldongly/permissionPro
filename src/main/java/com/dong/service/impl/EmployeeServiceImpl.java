package com.dong.service.impl;

import com.dong.domain.*;
import com.dong.mapper.EmployeeMapper;
import com.dong.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Semaphore;

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
        Page<Object> page = null;
        if (vo != null)
            page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Employee> employees = employeeMapper.selectAll(vo);

        PageListRes pageListRes = new PageListRes();
        if (vo != null)
            pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(employees);

        return pageListRes;
    }

    // 添加员工
    @Override
    public void addEmployee(Employee employee) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        employee.setSalt(uuid);
        Md5Hash hash = new Md5Hash(employee.getPassword(), employee.getSalt(), 2);
        employee.setPassword(hash.toString());

        if (employee.getState() == null) employee.setState(true);
        if (employee.getAdmin() == null) employee.setAdmin(false);

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

    @Override
    public Employee myProfile() {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        Department department = employeeMapper.getDepartmentByEid(employee.getId());
        employee.setDepartment(department);
        return employee;
    }

    @Override
    public AjaxRes updateProfile(Employee employee) {
        AjaxRes res = new AjaxRes();
        try {
            Employee self = (Employee) SecurityUtils.getSubject().getPrincipal();
            self.setRealName(employee.getRealName());
            self.setTel(employee.getTel());
            self.setEmail(employee.getEmail());
            employeeMapper.updateProfile(self);
            // 更新 Subject
            SimplePrincipalCollection collection = new SimplePrincipalCollection(self, self.getUsername());
            SecurityUtils.getSubject().runAs(collection);

            res.setSuccess(true);
            res.setMsg("更新成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg("更新失败");
        }
        return res;
    }

    @Override
    public AjaxRes updatePassword(String newPwd) {
        AjaxRes res = new AjaxRes();
        try {
            Employee self = (Employee) SecurityUtils.getSubject().getPrincipal();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");     // 新的UUID
            self.setSalt(uuid);
            Md5Hash hash = new Md5Hash(newPwd, self.getSalt(), 2);
            self.setPassword(hash.toString());
            employeeMapper.updatePassword(self);
            // 更新 Subject
            SimplePrincipalCollection collection = new SimplePrincipalCollection(self, self.getUsername());
            SecurityUtils.getSubject().runAs(collection);

            res.setSuccess(true);
            res.setMsg("修改成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg("修改失败");
        }
        return res;
    }

}
