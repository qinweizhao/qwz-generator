/*
 Navicat Premium Data Transfer

 Source Server         : Qwz_local
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : generator

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 14/03/2022 13:56:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_data_source_config
-- ----------------------------
DROP TABLE IF EXISTS `gen_data_source_config`;
CREATE TABLE `gen_data_source_config` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据源名称',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库密码',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据库连接',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='数据源';

-- ----------------------------
-- Records of gen_data_source_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for gen_template_entry
-- ----------------------------
DROP TABLE IF EXISTS `gen_template_entry`;
CREATE TABLE `gen_template_entry` (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_id` int DEFAULT NULL COMMENT '模板组Id',
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件夹路径/模板文件名称（支持占位符）',
  `type` tinyint(1) DEFAULT NULL COMMENT '文件类型 1：文件夹 2：模板文件',
  `parent_id` int DEFAULT NULL COMMENT '父级Id',
  `content` longtext COLLATE utf8mb4_general_ci COMMENT '模板内容',
  `engine_type` tinyint(1) DEFAULT NULL COMMENT '模板引擎类型 1：velocity',
  `remarks` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` bigint DEFAULT NULL COMMENT '逻辑删除标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_name_parent_id` (`deleted`,`group_id`,`parent_id`,`filename`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='模板文件目录项';

-- ----------------------------
-- Records of gen_template_entry
-- ----------------------------
BEGIN;
INSERT INTO `gen_template_entry` (`id`, `group_id`, `filename`, `type`, `parent_id`, `content`, `engine_type`, `remarks`, `deleted`, `create_time`, `update_time`) VALUES (1, 1, NULL, 1, 0, NULL, NULL, NULL, 0, '2022-03-14 13:22:43', NULL);
INSERT INTO `gen_template_entry` (`id`, `group_id`, `filename`, `type`, `parent_id`, `content`, `engine_type`, `remarks`, `deleted`, `create_time`, `update_time`) VALUES (2, 1, 'Controller', 1, 1, NULL, NULL, NULL, 0, '2022-03-14 13:23:04', NULL);
COMMIT;

-- ----------------------------
-- Table structure for gen_template_group
-- ----------------------------
DROP TABLE IF EXISTS `gen_template_group`;
CREATE TABLE `gen_template_group` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='模板组';

-- ----------------------------
-- Records of gen_template_group
-- ----------------------------
BEGIN;
INSERT INTO `gen_template_group` (`id`, `name`, `remarks`, `deleted`, `create_time`, `update_time`) VALUES (1, 'aa', NULL, 0, '2022-03-14 13:20:53', '2022-03-14 13:22:28');
COMMIT;

-- ----------------------------
-- Table structure for gen_template_property
-- ----------------------------
DROP TABLE IF EXISTS `gen_template_property`;
CREATE TABLE `gen_template_property` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `group_id` int DEFAULT NULL COMMENT '模板组ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
  `prop_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '属性键',
  `default_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '默认值',
  `required` tinyint(1) DEFAULT NULL COMMENT '必填，1：是，0：否',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注信息',
  `deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_group_id_prop_key` (`group_id`,`prop_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='模板属性配置';

-- ----------------------------
-- Records of gen_template_property
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
