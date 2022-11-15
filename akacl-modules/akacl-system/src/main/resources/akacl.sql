/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : akacl

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 15/11/2022 22:46:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cl_permission
-- ----------------------------
DROP TABLE IF EXISTS `cl_permission`;
CREATE TABLE `cl_permission`  (
  `id` bigint(19) NOT NULL COMMENT 'id',
  `permission_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限编码',
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限名称',
  `create_time` bigint(13) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(19) NULL DEFAULT NULL COMMENT '更新人id',
  `update_time` bigint(13) NULL DEFAULT NULL COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL COMMENT '是否启用',
  `deleted` tinyint(1) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_role
-- ----------------------------
DROP TABLE IF EXISTS `cl_role`;
CREATE TABLE `cl_role`  (
  `id` bigint(19) NOT NULL COMMENT 'id',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色名称',
  `create_user_id` bigint(19) NULL DEFAULT NULL COMMENT '创建人id',
  `create_time` bigint(13) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(19) NULL DEFAULT NULL COMMENT '更新人id',
  `update_time` bigint(13) NULL DEFAULT NULL COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL COMMENT '是否启用',
  `deleted` tinyint(1) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `cl_role_permission_relation`;
CREATE TABLE `cl_role_permission_relation`  (
  `id` bigint(19) NOT NULL COMMENT 'id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `permission_id` bigint(19) NULL DEFAULT NULL COMMENT '权限id',
  `create_time` bigint(13) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(19) NULL DEFAULT NULL COMMENT '更新人id',
  `update_time` bigint(13) NULL DEFAULT NULL COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL COMMENT '是否启用',
  `deleted` tinyint(1) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色_权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_user
-- ----------------------------
DROP TABLE IF EXISTS `cl_user`;
CREATE TABLE `cl_user`  (
  `id` bigint(19) NOT NULL COMMENT 'id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'username',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'password',
  `nike_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'phone',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'email',
  `create_user_id` bigint(19) NULL DEFAULT NULL COMMENT '创建人id',
  `create_time` bigint(13) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(19) NULL DEFAULT NULL COMMENT '更新人id',
  `update_time` bigint(13) NULL DEFAULT NULL COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL COMMENT '是否启用',
  `deleted` tinyint(1) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_role_relation`;
CREATE TABLE `cl_user_role_relation`  (
  `id` bigint(19) NOT NULL COMMENT 'id',
  `user_id` bigint(19) NOT NULL COMMENT '用户id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `create_user_id` bigint(19) NULL DEFAULT NULL COMMENT '创建人id',
  `create_time` bigint(13) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(19) NULL DEFAULT NULL COMMENT '更新人id',
  `update_time` bigint(13) NULL DEFAULT NULL COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL COMMENT '是否启用',
  `deleted` tinyint(1) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户角色' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
