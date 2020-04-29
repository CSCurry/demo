/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 29/04/2020 16:08:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo_user
-- ----------------------------
DROP TABLE IF EXISTS `demo_user`;
CREATE TABLE `demo_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `height` decimal(11, 2) NULL DEFAULT NULL COMMENT '身高',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_user
-- ----------------------------
INSERT INTO `demo_user` VALUES (1, '张无忌', 23, 180.00);
INSERT INTO `demo_user` VALUES (2, 'Tony', 12, 180.80);
INSERT INTO `demo_user` VALUES (3, '康康', 12, 180.80);
INSERT INTO `demo_user` VALUES (4, 'Maria', 12, 180.80);
INSERT INTO `demo_user` VALUES (5, 'Jack', 12, 180.80);
INSERT INTO `demo_user` VALUES (6, '胜多负少', 12, 180.80);
INSERT INTO `demo_user` VALUES (7, '合格后付', 12, 180.80);
INSERT INTO `demo_user` VALUES (8, '两句话', 12, 180.80);
INSERT INTO `demo_user` VALUES (9, '让他', 12, 180.80);
INSERT INTO `demo_user` VALUES (10, '娃儿', 12, 180.80);
INSERT INTO `demo_user` VALUES (11, '同一天', 12, 180.80);
INSERT INTO `demo_user` VALUES (12, '回复更丰富', 12, 180.80);
INSERT INTO `demo_user` VALUES (13, '轨道射灯', 12, 180.80);
INSERT INTO `demo_user` VALUES (14, '回复的', 12, 180.80);
INSERT INTO `demo_user` VALUES (15, '不吃', 12, 180.80);
INSERT INTO `demo_user` VALUES (16, '自行车', 12, 180.80);
INSERT INTO `demo_user` VALUES (17, '从VB从', 12, 180.80);
INSERT INTO `demo_user` VALUES (18, '女不女', 12, 180.80);
INSERT INTO `demo_user` VALUES (19, '没那么', 12, 180.80);
INSERT INTO `demo_user` VALUES (20, '那么那么难', 12, 180.80);
INSERT INTO `demo_user` VALUES (21, 'CVC', 12, 180.80);
INSERT INTO `demo_user` VALUES (22, '沃尔沃', 12, 180.80);
INSERT INTO `demo_user` VALUES (23, '二人台', 12, 180.80);
INSERT INTO `demo_user` VALUES (24, '任天野羊肉汤', 12, 180.80);
INSERT INTO `demo_user` VALUES (25, 'iuyy', 12, 180.80);
INSERT INTO `demo_user` VALUES (26, '驱蚊器二群', 12, 180.80);
INSERT INTO `demo_user` VALUES (27, '似睡非睡', 12, 180.80);
INSERT INTO `demo_user` VALUES (28, 'UIUC', 12, 180.80);
INSERT INTO `demo_user` VALUES (29, '去问问', 12, 180.80);
INSERT INTO `demo_user` VALUES (30, '企鹅额', 12, 180.80);
INSERT INTO `demo_user` VALUES (31, '啊啊啊', 12, 180.80);
INSERT INTO `demo_user` VALUES (32, '钢结构', 12, 180.80);
INSERT INTO `demo_user` VALUES (33, '苟富贵', 12, 180.80);
INSERT INTO `demo_user` VALUES (34, '大幅度', 12, 180.80);
INSERT INTO `demo_user` VALUES (35, '会更好', 12, 180.80);
INSERT INTO `demo_user` VALUES (36, '离开了', 12, 180.80);
INSERT INTO `demo_user` VALUES (37, '美女', 12, 180.80);
INSERT INTO `demo_user` VALUES (38, '二恶', 12, 180.80);

SET FOREIGN_KEY_CHECKS = 1;
