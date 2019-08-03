# 创建数据表顺序
1. 创建部门数据表 department
2. 创建权限表 permission，添加permission表数据
3. 创建菜单表 menu，添加menu表数据 *关联了permission表*
4. 创建角色表 role
5. 创建角色权限关系表 role_permission_rel *关联了role和permission表*
6. 创建员工表 employee *关联了depart表*
7. 创建员工角色关系表 employee_role_rel *关联了employee和role表*
8. 创建系统日志表 systemlog
