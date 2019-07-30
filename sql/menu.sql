
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(50) NOT NULL,
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_url_uindex` (`url`),
  KEY `parent_id` (`parent_id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`),
  CONSTRAINT `menu_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


-- 先添加权限表permission中的数据
INSERT INTO `menu`(`id`, `text`, `url`, `parent_id`, `permission_id`) VALUES (1, '系统管理', NULL, NULL, NULL);
INSERT INTO `menu`(`id`, `text`, `url`, `parent_id`, `permission_id`) VALUES (2, '员工管理', '/employee', 1, 4);
INSERT INTO `menu`(`id`, `text`, `url`, `parent_id`, `permission_id`) VALUES (3, '角色权限管理', '/role', 1, 5);
INSERT INTO `menu`(`id`, `text`, `url`, `parent_id`, `permission_id`) VALUES (4, '菜单管理', '/menu', 1, 6);