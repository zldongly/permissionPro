CREATE TABLE `systemlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `optime` datetime DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `func` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `params` varchar(511) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=258 DEFAULT CHARSET=utf8;
