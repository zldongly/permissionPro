package com.dong.service.impl;

import com.dong.domain.Department;
import com.dong.mapper.DepartmentMapper;
import com.dong.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongly on 2019/7/20
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartment() {
        return departmentMapper.selectAll();
    }
}
