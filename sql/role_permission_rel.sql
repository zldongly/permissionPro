-- 先创建role 和 permission 数据表
CREATE TABLE `role_permission_rel` (
  `rid` bigint(20) NOT NULL,
  `pid` bigint(20) NOT NULL,
  PRIMARY KEY (`rid`,`pid`),
  KEY `pid` (`pid`),
  CONSTRAINT `role_permission_rel_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_permission_rel_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `permission` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
