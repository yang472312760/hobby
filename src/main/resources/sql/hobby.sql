/*
Navicat MySQL Data Transfer

Source Server         : 192.168.220.128
Source Server Version : 50562
Source Host           : 192.168.220.128:3306
Source Database       : hobby

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2020-06-05 10:46:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_demo
-- ----------------------------
DROP TABLE IF EXISTS `sys_demo`;
CREATE TABLE `sys_demo` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_demo
-- ----------------------------
INSERT INTO `sys_demo` VALUES ('1', 'yang', '1');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) DEFAULT NULL,
  `permission_type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modifier_id` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `is_delete` varchar(255) DEFAULT NULL,
  `is_temp` varchar(255) DEFAULT NULL,
  `locking` varchar(255) DEFAULT NULL,
  `locking_modify_time` datetime DEFAULT NULL,
  `sys_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'demo', 'demo', null, null, '1', 'demo', 'select', 'yang', '1', '2020-06-05 09:44:26', 'yang', '1', '2020-06-05 09:44:31', '0', '0', '1', '2020-06-05 09:44:37', '1');
INSERT INTO `sys_permission` VALUES ('2', 'demo', 'demo', null, null, '2', 'demo', 'insert', 'yang', '1', '2020-06-05 09:45:15', 'yang', '1', '2020-06-05 09:45:20', '0', '0', '1', '2020-06-05 09:45:27', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `role_type` varchar(255) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modifier_id` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `is_delete` varchar(255) DEFAULT NULL,
  `is_temp` varchar(255) DEFAULT NULL,
  `locking` varchar(255) DEFAULT NULL,
  `locking_modify_time` datetime DEFAULT NULL,
  `sys_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '管理员', 'role', null, '1', 'yang', '1', '2020-06-05 09:42:08', 'yang', '1', '2020-06-05 09:42:14', '0', '0', '1', '2020-06-05 09:42:20', '1');
INSERT INTO `sys_role` VALUES ('2', '系统管理员', '系统管理员', 'admin', null, '2', 'yang', '1', '2020-06-05 09:42:53', 'yang', '1', '2020-06-05 09:43:00', '0', '0', '1', '2020-06-05 09:43:06', '1');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '1', '2');
INSERT INTO `sys_role_permission` VALUES ('3', '2', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `hand_image_url` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_account_non_expired` varchar(255) DEFAULT NULL,
  `is_account_non_locked` varchar(255) DEFAULT NULL,
  `is_credentials_non_expired` varchar(255) DEFAULT NULL,
  `is_enabled` varchar(255) DEFAULT NULL,
  `is_remember_me` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modifier_id` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `is_delete` varchar(255) DEFAULT NULL,
  `is_temp` varchar(255) DEFAULT NULL,
  `locking` varchar(255) DEFAULT NULL,
  `locking_modify_time` datetime DEFAULT NULL,
  `sys_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'yang', 'yang', 'ce623d173685d9841999f783edc7e88f', null, '1', '111', null, '0', '0', '0', '1', '1', 'yang', '1', '2020-06-05 09:38:25', 'yang', '1', '2020-06-05 09:38:35', '0', '0', '1', '2020-06-05 09:38:42', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2', '1');
