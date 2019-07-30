CREATE TABLE `role` (
  `rid` bigint(20) NOT NULL AUTO_INCREMENT,
  `rnum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`rid`),
  UNIQUE KEY `role_rnum_uindex` (`rnum`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
