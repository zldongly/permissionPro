package com.dong.web.realm;

import com.dong.domain.Employee;
import com.dong.service.EmployeeService;
import com.dong.service.RoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongly on 2019/7/23
 */

public class EmployeeRealm extends AuthorizingRealm {

    @Autowired
    private EmployeeService employeeService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("AuthorizationInfo");
        Employee employee = (Employee) principalCollection.getPrimaryPrincipal();

        // 查询权限
        List<String> roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();

        if (employee.getState()) {          // 在职员工才拥有权限
            if (employee.getAdmin()) {
                permissions.add("*:*");     // 管理员拥有所有权限
            } else {
                roles = employeeService.getRolesByEid(employee.getId());
                permissions = employeeService.getPermissionsByEid(employee.getId());
            }
        }

        // 授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);

        return info;
    }


    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("come AuthenticationInfo");
        String username = (String) token.getPrincipal();
        System.out.println(username);

        Employee employee = employeeService.getEmployeeWithUsername(username);
        System.out.println(employee);

        if (employee == null)
            return null;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                employee, employee.getPassword(), ByteSource.Util.bytes(employee.getSalt()), this.getName());

        return info;
    }
}
