CREATE TABLE `permission` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `pname` varchar(50) DEFAULT NULL,
  `presource` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


-- 添加数据
INSERT INTO `permission`(`pid`, `pname`, `presource`) VALUES (1, '员工添加', 'employee:add');
INSERT INTO `permission`(`pid`, `pname`, `presource`) VALUES (2, '员工删除', 'employee:delete');
INSERT INTO `permission`(`pid`, `pname`, `presource`) VALUES (3, '员工编辑', 'employee:edit');
INSERT INTO `permission`(`pid`, `pname`, `presource`) VALUES (4, '员工主页', 'employee:index');
INSERT INTO `permission`(`pid`, `pname`, `presource`) VALUES (5, '角色权限主页', 'role:index');
INSERT INTO `permission`(`pid`, `pname`, `presource`) VALUES (6, '菜单主页', 'menu:index');
INSERT INTO `permission`(`pid`, `pname`, `presource`) VALUES (7, '部门主页', 'department:index');