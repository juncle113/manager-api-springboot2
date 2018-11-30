/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : cc_dapp_dev

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 30/11/2018 14:19:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for manager_admin
-- ----------------------------
DROP TABLE IF EXISTS `manager_admin`;
CREATE TABLE `manager_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `user_name` varchar(16) DEFAULT NULL COMMENT '账号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `name` varchar(16) DEFAULT NULL COMMENT '姓名',
  `role_type` int(11) DEFAULT NULL COMMENT '角色类型（1：超级管理员，2：普通管理员）',
  `status` int(11) DEFAULT NULL COMMENT '状态(1：启用，2：禁用)',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `modified_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modified_by` int(11) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='后台管理-管理账号表';

-- ----------------------------
-- Records of manager_admin
-- ----------------------------
BEGIN;
INSERT INTO `manager_admin` VALUES (1, 'root', 'f9c7ae92238aa522def86c52e9d73231', '超级管理员', 1, 1, '2018-09-06 16:24:50', 1, '2018-11-15 17:02:49', 1);
INSERT INTO `manager_admin` VALUES (2, 'admin', 'f9c7ae92238aa522def86c52e9d73231', 'admin', 2, 2, '2018-10-05 22:48:37', 1, '2018-11-16 13:28:31', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
