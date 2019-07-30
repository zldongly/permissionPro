CREATE TABLE `employee_role_rel` (
  `eid` bigint(20) NOT NULL,
  `rid` bigint(20) NOT NULL,
  PRIMARY KEY (`eid`,`rid`),
  KEY `rid` (`rid`),
  CONSTRAINT `employee_role_rel_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `employee_role_rel_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;