-- 先创建 department 数据表
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `salt` varchar(32) DEFAULT NULL,
  `input_time` datetime DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `state` tinyint(1) DEFAULT '1',
  `admin` tinyint(1) DEFAULT '0',
  `dep_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_username_uindex` (`username`),
  KEY `dep_id` (`dep_id`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`dep_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;


-- 添加一个初始管理员 用户名admin 密码admin
INSERT INTO 
`employee`(`id`, `username`, `password`, `salt`, `input_time`, `tel`, `email`, `state`, `admin`, `dep_id`)
VALUES (1, 'admin', '1be8ae3613133711029d11ac391c1129', 'eb2f5466a2b94153a3f5dbdc8f61ca73', NULL, NULL, NULL, 1, 1, NULL);
