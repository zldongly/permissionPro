package com.dong.mapper;

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
}