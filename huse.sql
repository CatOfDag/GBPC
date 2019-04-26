/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50553
 Source Host           : localhost:3306
 Source Schema         : huse

 Target Server Type    : MySQL
 Target Server Version : 50553
 File Encoding         : 65001

 Date: 26/04/2019 22:03:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for huse_admin
-- ----------------------------
DROP TABLE IF EXISTS `huse_admin`;
CREATE TABLE `huse_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `admin_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员名',
  `password` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `state` tinyint(1) NOT NULL COMMENT '启用状态',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `role` enum('user','su') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of huse_admin
-- ----------------------------
INSERT INTO `huse_admin` VALUES (1, 'superuser', 'superuser', '13574654443', 1, '超级管理员', 'su');
INSERT INTO `huse_admin` VALUES (2, 'user', '1234', '15874677727', 1, '普通用户', 'user');
INSERT INTO `huse_admin` VALUES (3, '冯亮', 'sdad250', '15874677728', 1, '冯亮帅的一批', 'user');
INSERT INTO `huse_admin` VALUES (4, 'zjj', 'jj123', '15200977801', 1, '冯亮的兄弟张jiji', 'user');
INSERT INTO `huse_admin` VALUES (5, 'qd', '123', '18222222222', 1, '冯亮兄弟之缺德', 'user');
INSERT INTO `huse_admin` VALUES (7, 'xb', 'xbbb', '13967421654', 1, '冯亮兄弟之小白', 'user');
INSERT INTO `huse_admin` VALUES (8, 'lx', 'lx888', '15844489773', 1, '小帅哥雷新', 'user');
INSERT INTO `huse_admin` VALUES (9, 'wzx', 'wzx111', '13599384211', 1, '王子小帅哥', 'user');
INSERT INTO `huse_admin` VALUES (20, '桂佳文', 'cool', '15874653211', 1, '龟甲文', 'user');
INSERT INTO `huse_admin` VALUES (21, '李子健', 'shabi', '15688887724', 1, '撒上大声地撒', 'user');
INSERT INTO `huse_admin` VALUES (22, '杠精健', '1233', '15581485719', 1, '科院无敌杠精', 'user');
INSERT INTO `huse_admin` VALUES (23, 'zz22', '11111111', '18907464088', 0, '1087', 'su');

-- ----------------------------
-- Table structure for huse_cadre
-- ----------------------------
DROP TABLE IF EXISTS `huse_cadre`;
CREATE TABLE `huse_cadre`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cadre_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '干部姓名',
  `job` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位',
  `state` tinyint(1) NOT NULL COMMENT '启用状态',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `password` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role` enum('干部') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cadre_name`(`cadre_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of huse_cadre
-- ----------------------------
INSERT INTO `huse_cadre` VALUES (1, '王干子', '哈哈哈', 0, '我是一个爱打lol和王者的嘤嘤怪', 'ssss', '干部');
INSERT INTO `huse_cadre` VALUES (2, '二马亮', '网吧经理', 1, '他是网吧经理', '2222', '干部');
INSERT INTO `huse_cadre` VALUES (3, '钟组新', '网吧网名', 1, '普通人啦', '22222', '干部');
INSERT INTO `huse_cadre` VALUES (4, '杠子健', '网吧试喷员', 1, '傻吊一个', '3333', '干部');
INSERT INTO `huse_cadre` VALUES (5, 'wk', '凯特威公子', 1, '牛逼', 'wkkk', '干部');
INSERT INTO `huse_cadre` VALUES (6, 'zjj', 'hotel男神', 0, '你知道加州招待所吗', 'adsad', '干部');
INSERT INTO `huse_cadre` VALUES (7, '黑岩', '学习小霸王', 0, 'java牛逼', 'study', '干部');
INSERT INTO `huse_cadre` VALUES (8, 'mmw', '富两代', 1, '虽然我很有钱但是我也爱学习,考托福的男人', 'rich', '干部');
INSERT INTO `huse_cadre` VALUES (9, 'ltw', '学习黑岩', 1, '向黑岩学习', 'lwt', '干部');
INSERT INTO `huse_cadre` VALUES (18, '王子轩', '厕所委员', 1, '哈哈', '1236544', '干部');
INSERT INTO `huse_cadre` VALUES (19, '假乃亮', '绿帽社', 1, 'PGone他是我弟弟', 'jll', '干部');

-- ----------------------------
-- Table structure for huse_file
-- ----------------------------
DROP TABLE IF EXISTS `huse_file`;
CREATE TABLE `huse_file`  (
  `id` int(11) NOT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名',
  `header_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件储存的路径',
  `time` datetime NULL DEFAULT NULL COMMENT '保存的时间',
  `card_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外键干部名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `huse_file_ibfk_1`(`card_name`) USING BTREE,
  CONSTRAINT `huse_file_ibfk_1` FOREIGN KEY (`card_name`) REFERENCES `huse_cadre` (`cadre_name`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for huse_log
-- ----------------------------
DROP TABLE IF EXISTS `huse_log`;
CREATE TABLE `huse_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ip地址',
  `identify` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '生份',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for huse_participant
-- ----------------------------
DROP TABLE IF EXISTS `huse_participant`;
CREATE TABLE `huse_participant`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `PIN` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '个人识别码',
  `role` enum('校长','干部','教师','群众') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参与者类型',
  `endtime` datetime NOT NULL COMMENT '有效截止时间',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '是否投票',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `PIN`(`PIN`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of huse_participant
-- ----------------------------
INSERT INTO `huse_participant` VALUES (1, 'A0000001', '校长', '2019-04-26 14:16:49', 1);
INSERT INTO `huse_participant` VALUES (2, 'A8888888', '干部', '2019-04-26 14:17:45', 1);
INSERT INTO `huse_participant` VALUES (3, 'ASDSASD', '校长', '2019-04-26 17:11:20', 1);
INSERT INTO `huse_participant` VALUES (4, 'FAFSAFAS', '干部', '2019-04-26 22:00:00', 1);
INSERT INTO `huse_participant` VALUES (5, 'ASSDADSA', '干部', '2019-04-26 17:14:05', 1);
INSERT INTO `huse_participant` VALUES (6, 'DSADAS', '校长', '2019-04-22 00:00:00', 1);
INSERT INTO `huse_participant` VALUES (7, 'DASDASD', '干部', '2019-04-23 00:00:00', 1);
INSERT INTO `huse_participant` VALUES (8, 'SDADSA', '干部', '2019-04-26 17:14:49', 1);
INSERT INTO `huse_participant` VALUES (9, 'DSADAS', '校长', '2019-04-30 00:00:00', 1);
INSERT INTO `huse_participant` VALUES (10, 'WEQEQW', '校长', '2019-05-26 00:00:00', 1);
INSERT INTO `huse_participant` VALUES (11, 'DSFG', '干部', '2019-04-26 17:15:31', 1);
INSERT INTO `huse_participant` VALUES (12, 'DSAGSDC', '干部', '2019-04-26 17:15:49', 1);
INSERT INTO `huse_participant` VALUES (13, '12345678', '校长', '2019-04-30 00:00:00', 1);
INSERT INTO `huse_participant` VALUES (14, 'ASDFGHJK', '群众', '2019-04-30 00:00:00', 1);

-- ----------------------------
-- Table structure for huse_score
-- ----------------------------
DROP TABLE IF EXISTS `huse_score`;
CREATE TABLE `huse_score`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `PIN` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参与者的pin',
  `cadre_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '干部姓名',
  `alias` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投票的简写',
  `vote_time` datetime NOT NULL COMMENT '投票的时间',
  `virtue` int(11) NULL DEFAULT NULL COMMENT '德',
  `ability` int(11) NULL DEFAULT NULL COMMENT '能',
  `diligence` int(11) NULL DEFAULT NULL COMMENT '勤',
  `feats` int(11) NULL DEFAULT NULL COMMENT '绩',
  `honest` int(11) NULL DEFAULT NULL COMMENT '廉',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `PIN`(`PIN`) USING BTREE,
  INDEX `cadre_name`(`cadre_name`) USING BTREE,
  INDEX `alias`(`alias`) USING BTREE,
  CONSTRAINT `huse_score_ibfk_1` FOREIGN KEY (`PIN`) REFERENCES `huse_participant` (`PIN`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `huse_score_ibfk_2` FOREIGN KEY (`cadre_name`) REFERENCES `huse_cadre` (`cadre_name`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `huse_score_ibfk_3` FOREIGN KEY (`alias`) REFERENCES `huse_vote` (`alias`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for huse_vote
-- ----------------------------
DROP TABLE IF EXISTS `huse_vote`;
CREATE TABLE `huse_vote`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投票名',
  `alias` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投票别名',
  `begin_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '截止时间',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `alias`(`alias`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of huse_vote
-- ----------------------------
INSERT INTO `huse_vote` VALUES (1, '19年中层干部民主投票', '19投票', '2019-04-26 19:10:47', '2019-06-30 19:10:59', '19年中层干部投票');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1005 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1000, 'fl', 'fl5288', '15847677727');
INSERT INTO `t_user` VALUES (1001, 'qd', 'qd2fl', '123456789');
INSERT INTO `t_user` VALUES (1002, 'zjj', 'zjjgay', '1236549871');
INSERT INTO `t_user` VALUES (1003, 'ly', 'lyaifl', '1236547895');
INSERT INTO `t_user` VALUES (1004, 'cxk', 'jntm', '545612131');

SET FOREIGN_KEY_CHECKS = 1;
