/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : wuchaofei

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-06-16 16:52:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `channel`
-- ----------------------------
DROP TABLE IF EXISTS `channel`;
CREATE TABLE `channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_id` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `channel_id` (`channel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of channel
-- ----------------------------
INSERT INTO `channel` VALUES ('1', '1001', '苹果官方市场', '', '2017-03-31 19:27:16', '2017-03-31 19:27:16');
INSERT INTO `channel` VALUES ('2', '2024', '我买网官网android', '', '2017-03-31 19:27:16', '2017-03-31 19:27:16');
INSERT INTO `channel` VALUES ('3', '3001', '安卓市场推广3001', '', '2017-03-31 19:27:16', '2017-03-31 19:27:16');

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `permissionname` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'admin', null, '管理员权限');
INSERT INTO `permission` VALUES ('2', 'test1', null, 'test1');
INSERT INTO `permission` VALUES ('3', 'test2', null, 'test2');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '管理员');
INSERT INTO `role` VALUES ('2', 'test', 'test');
INSERT INTO `role` VALUES ('3', 'test1', 'test1');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role` int(11) unsigned NOT NULL,
  `permission` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('2', '2', '2');
INSERT INTO `role_permission` VALUES ('3', '2', '3');
INSERT INTO `role_permission` VALUES ('4', '3', '2');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT '',
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `mobile` varchar(255) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `mail` varchar(255) DEFAULT NULL,
  `avatar` longtext,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', 'jspdba', 'A1F60A3530A8BFE732C0BD72BC509A7F', '13661303427', '1', null, '1', '2017-03-30 13:37:50', '2017-03-30 13:37:50');
INSERT INTO `user` VALUES ('2', '', 'test1', '098F6BCD4621D373CADE4E832627B4F6', '', '1', null, null, '2017-06-16 16:27:15', '2017-06-16 16:27:15');
INSERT INTO `user` VALUES ('3', '', 'test2', '098F6BCD4621D373CADE4E832627B4F6', '', '1', null, null, '2017-06-16 16:27:23', '2017-06-16 16:27:23');
INSERT INTO `user` VALUES ('4', '', 'test3', '098F6BCD4621D373CADE4E832627B4F6', '', '1', null, null, '2017-06-16 16:27:29', '2017-06-16 16:27:29');
INSERT INTO `user` VALUES ('5', '', 'test4', '098F6BCD4621D373CADE4E832627B4F6', '', '1', null, null, '2017-06-16 16:27:35', '2017-06-16 16:27:35');
INSERT INTO `user` VALUES ('6', '', 'test5', '098F6BCD4621D373CADE4E832627B4F6', '', '1', null, null, '2017-06-16 16:27:41', '2017-06-16 16:27:41');
INSERT INTO `user` VALUES ('7', '', 'test6', '098F6BCD4621D373CADE4E832627B4F6', '', '1', null, null, '2017-06-16 16:27:50', '2017-06-16 16:27:50');
INSERT INTO `user` VALUES ('8', '', 'test7', '098F6BCD4621D373CADE4E832627B4F6', '', '1', null, null, '2017-06-16 16:27:55', '2017-06-16 16:27:55');
INSERT INTO `user` VALUES ('9', '', 'test8', '098F6BCD4621D373CADE4E832627B4F6', '', '1', null, null, '2017-06-16 16:28:01', '2017-06-16 16:28:01');
INSERT INTO `user` VALUES ('10', '', 'test9', '098F6BCD4621D373CADE4E832627B4F6', '', '1', null, null, '2017-06-16 16:28:07', '2017-06-16 16:28:07');
INSERT INTO `user` VALUES ('11', '', 'test10', '098F6BCD4621D373CADE4E832627B4F6', '', '1', null, null, '2017-06-16 16:28:14', '2017-06-16 16:28:14');
INSERT INTO `user` VALUES ('12', '', 'test11', '098F6BCD4621D373CADE4E832627B4F6', '', '1', null, null, '2017-06-16 16:28:21', '2017-06-16 16:28:21');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');
INSERT INTO `user_role` VALUES ('3', '3', '2');
INSERT INTO `user_role` VALUES ('4', '3', '3');
