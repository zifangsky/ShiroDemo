-- ----------------------------
-- Table structure for usr_func
-- ----------------------------
DROP TABLE IF EXISTS `usr_func`;
CREATE TABLE `usr_func` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usr_func
-- ----------------------------
INSERT INTO `usr_func` VALUES ('1', '用户管理-查询', null, 'YHGL:CX', null, 'enable');
INSERT INTO `usr_func` VALUES ('2', '用户管理-新增', null, 'YHGL:XZ', null, 'enable');
INSERT INTO `usr_func` VALUES ('3', '用户管理-编辑', null, 'YHGL:BJ', null, 'enable');
INSERT INTO `usr_func` VALUES ('4', '用户管理-停用', null, 'YHGL:TY', null, 'enable');
INSERT INTO `usr_func` VALUES ('5', '用户管理-启用', null, 'YHGL:QY', null, 'enable');
INSERT INTO `usr_func` VALUES ('6', '用户管理-删除', null, 'YHGL:SC', null, 'enable');
INSERT INTO `usr_func` VALUES ('7', '文章管理-查询', null, 'WZGL:CX', null, 'enable');
INSERT INTO `usr_func` VALUES ('8', '文章管理-新增', null, 'WZGL:XZ', null, 'enable');
INSERT INTO `usr_func` VALUES ('9', '文章管理-编辑', null, 'WZGL:BJ', null, 'enable');
INSERT INTO `usr_func` VALUES ('10', '文章管理-删除', null, 'WZGL:SC', null, 'enable');

-- ----------------------------
-- Table structure for usr_role
-- ----------------------------
DROP TABLE IF EXISTS `usr_role`;
CREATE TABLE `usr_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usr_role
-- ----------------------------
INSERT INTO `usr_role` VALUES ('1', 'manager', '管理员');
INSERT INTO `usr_role` VALUES ('2', 'editor', '编辑');
INSERT INTO `usr_role` VALUES ('3', 'author', '作者');
INSERT INTO `usr_role` VALUES ('4', 'subscriber', '订阅者');
INSERT INTO `usr_role` VALUES ('5', 'contributor', '投稿者');

-- ----------------------------
-- Table structure for usr_role_func
-- ----------------------------
DROP TABLE IF EXISTS `usr_role_func`;
CREATE TABLE `usr_role_func` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `funcId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `roleId` FOREIGN KEY (`roleId`) REFERENCES `usr_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usr_role_func
-- ----------------------------
INSERT INTO `usr_role_func` VALUES ('1', '1', '1');
INSERT INTO `usr_role_func` VALUES ('2', '1', '2');
INSERT INTO `usr_role_func` VALUES ('3', '1', '3');
INSERT INTO `usr_role_func` VALUES ('4', '1', '4');
INSERT INTO `usr_role_func` VALUES ('5', '1', '5');
INSERT INTO `usr_role_func` VALUES ('6', '1', '6');
INSERT INTO `usr_role_func` VALUES ('7', '1', '7');
INSERT INTO `usr_role_func` VALUES ('8', '1', '8');
INSERT INTO `usr_role_func` VALUES ('9', '1', '9');
INSERT INTO `usr_role_func` VALUES ('10', '1', '10');
INSERT INTO `usr_role_func` VALUES ('11', '2', '7');
INSERT INTO `usr_role_func` VALUES ('12', '2', '8');
INSERT INTO `usr_role_func` VALUES ('13', '2', '9');
INSERT INTO `usr_role_func` VALUES ('14', '2', '10');
INSERT INTO `usr_role_func` VALUES ('15', '3', '7');
INSERT INTO `usr_role_func` VALUES ('16', '3', '8');
INSERT INTO `usr_role_func` VALUES ('17', '3', '9');
INSERT INTO `usr_role_func` VALUES ('18', '4', '7');
INSERT INTO `usr_role_func` VALUES ('19', '5', '8');

-- ----------------------------
-- Table structure for usr_user
-- ----------------------------
DROP TABLE IF EXISTS `usr_user`;
CREATE TABLE `usr_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `channelId` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usr_user
-- ----------------------------
INSERT INTO `usr_user` VALUES ('1', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '110', 'admin@zifangsky.cn', '2016-10-04 10:33:23', '2016-10-06 10:38:40', '1', 'enable');
INSERT INTO `usr_user` VALUES ('2', 'test', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '3456789', 'test@110.com', '2016-10-18 18:25:12', '2016-10-19 18:25:17', '2', 'enable');
INSERT INTO `usr_user` VALUES ('5', 'zifangsky', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '911', 'admin@zifangsky.cn', '2016-10-20 11:46:45', '2016-10-20 11:46:57', '1', 'enable');
INSERT INTO `usr_user` VALUES ('6', 'sub', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, null, null, null, null, 'disable');
INSERT INTO `usr_user` VALUES ('7', 'contributor', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, null, null, null, null, 'disable');

-- ----------------------------
-- Table structure for usr_user_role
-- ----------------------------
DROP TABLE IF EXISTS `usr_user_role`;
CREATE TABLE `usr_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `usr_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usr_user_role
-- ----------------------------
INSERT INTO `usr_user_role` VALUES ('1', '1', '1');
INSERT INTO `usr_user_role` VALUES ('2', '5', '3');
INSERT INTO `usr_user_role` VALUES ('3', '5', '5');
