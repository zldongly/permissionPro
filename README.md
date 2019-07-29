# 权限管理系统 permission project

## 开发环境
* windows 10
* IntelliJ IDEA 2019
* tomcat 9
* MySQL 8.0
* maven 3.6

## 说明
使用 spring MVC + spring + MyBatis （ssm框架）开发  
前端使用 Jquery EasyUI

## 功能实现
### 登录
![login](./screenshots/login.JPG)  
用户名和密码进行登录，使用Shiro安全框架验证用户身份，密码通过MD5散列函数加密
### 菜单
![menu](./screenshots/menu.JPG)  
登录后根据用户所拥有的权限给出相应的树形菜单
### 员工管理
![employee](./screenshots/employee.JPG)  
员工管理页面展示所有员工信息，分页每页显示10名员工。  
上方工具栏中输入关键可以查找到包含该关键字的员工，可以查找相应的用户名、电话或邮箱。  
拥有权限的用户可以添加、编辑员工。可以添加单个，也可以通过Excel批量添加。  
![add](./screenshots/add.JPG)
![excelAdd](./screenshots/excelAdd.JPG)  
通过Excel添加的需要先下载模板，按照对应的格式填写，再上传Excel文件。
### 角色权限管理
![role](./screenshots/role.JPG)
管理员设置不同角色拥有的权限，在员工管理页中给员工添加角色  
![roleEdit](./screenshots/roleEdit.JPG)  
一个角色可以拥有多个权限
### 日志记录
使用切面编程，在方法后面追加日志记录  
在调用每个控制器中的方法时记录下每个用户IP、时间、调用的方法名和参数
