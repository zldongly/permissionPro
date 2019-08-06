package com.dong.mapper;

import com.dong.domain.Department;
import com.dong.domain.Employee;
import com.dong.domain.QueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll(QueryVo vo);

    int updateByPrimaryKey(Employee record);

    // 更新离职状态
    void updateState(Long id);

    // 添加员工、角色关系
    int insertRoleRel(@Param("eid") Long id, @Param("rid") Long rid);

    // 删除角色关系
    void deleteRoleRel(Long eid);

    Employee getEmployeeWithUsername(String username);

    // 根据 employee id 查询 role rnum
    List<String> getRolesByEid(Long eid);

    // 根据 employee id 查询 permission
    List<String> getPermissionsByEid(Long eid);

    // 根据employee id 查询 department
    Department getDepartmentByEid(Long eid);

    // 修改个人信息
    int updateProfile(Employee employee);

    // 修改密码
    int updatePassword(Employee self);
}