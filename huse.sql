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

 Date: 04/05/2019 21:41:53
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
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of huse_admin
-- ----------------------------
INSERT INTO `huse_admin` VALUES (1, 'superuser', 'superuser', '13574654443', 0, '超级管理员', 'su');
INSERT INTO `huse_admin` VALUES (2, 'user', '1234', '15874677727', 1, '普通用户', 'user');
INSERT INTO `huse_admin` VALUES (3, 'fl', '11111', '15874677739', 0, '冯亮包夜开黑的样子帅的一批', 'su');
INSERT INTO `huse_admin` VALUES (4, 'zjj', 'jj123', '15200977801', 1, '冯亮的兄弟张jiji', 'user');
INSERT INTO `huse_admin` VALUES (5, 'qd', '123', '18222222222', 1, '冯亮兄弟之缺德', 'user');
INSERT INTO `huse_admin` VALUES (7, 'xb', 'xbbb', '13967421654', 1, '冯亮兄弟之小白', 'user');
INSERT INTO `huse_admin` VALUES (8, 'lx', 'lx888', '15844489773', 1, '小帅哥雷新', 'user');
INSERT INTO `huse_admin` VALUES (9, 'wzx', 'wzx111', '13599384211', 1, '王子小帅哥', 'user');
INSERT INTO `huse_admin` VALUES (20, '桂佳文', 'cool', '15874653211', 1, '龟甲文', 'user');
INSERT INTO `huse_admin` VALUES (21, '李子健', 'shabi', '15688887724', 1, '撒上大声地撒', 'user');
INSERT INTO `huse_admin` VALUES (24, 'sbs', 'dsadas', '15707472222', 1, '打撒大撒', 'user');

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
  `avote_lias` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参与的投票项目',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cadre_name`(`cadre_name`) USING BTREE,
  INDEX `avote_lias`(`avote_lias`) USING BTREE,
  CONSTRAINT `huse_cadre_ibfk_1` FOREIGN KEY (`avote_lias`) REFERENCES `huse_vote` (`alias`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of huse_cadre
-- ----------------------------
INSERT INTO `huse_cadre` VALUES (1, '王干子', '呼呼呼', 0, '我是一个爱打lol和王者的嘤嘤怪', 'ssss', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (2, '二马亮', '网吧经理', 1, '他是网吧经理', '2222', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (3, '钟组新', '网吧网名', 1, '普通人啦', '22222', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (4, '杠子健', '网吧试喷员', 1, '傻吊一个', '3333', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (5, 'wk', '凯特威公子', 1, '牛逼', 'wkkk', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (6, 'zjj', 'hotel男神', 0, '你知道加州招待所吗', 'adsad', '干部', '18投票');
INSERT INTO `huse_cadre` VALUES (7, '黑岩', '学习小霸王', 0, 'java牛逼', 'study', '干部', '18投票');
INSERT INTO `huse_cadre` VALUES (8, 'mmw', '富两代', 1, '虽然我很有钱但是我也爱学习,考托福的男人', 'rich', '干部', '18投票');
INSERT INTO `huse_cadre` VALUES (9, 'ltw', '学习黑岩', 1, '向黑岩学习', 'lwt', '干部', '18投票');
INSERT INTO `huse_cadre` VALUES (18, '王子轩', '厕所委员', 1, '哈哈', '1236544', '干部', '18投票');
INSERT INTO `huse_cadre` VALUES (19, '假乃亮', '绿帽社社长', 1, 'PGone他是我弟弟', 'jll', '干部', '18投票');
INSERT INTO `huse_cadre` VALUES (23, '张二毛', '战忽局部长', 1, '印度流批', '12222', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (24, '戴狗蛋', '学习委员', 1, '爱学习哟', 'gd888', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (25, '王二狗', '团鸡书', 1, '我系团支书', 'bg250', '干部', '19投票');

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
  `state` tinyint(1) NOT NULL COMMENT '是否投票',
  `vote_alias` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投票别名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `PIN`(`PIN`) USING BTREE,
  INDEX `vote_alias`(`vote_alias`) USING BTREE,
  CONSTRAINT `huse_participant_ibfk_1` FOREIGN KEY (`vote_alias`) REFERENCES `huse_vote` (`alias`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of huse_participant
-- ----------------------------
INSERT INTO `huse_participant` VALUES (17, 'ASDFGHJK', '校长', '2019-04-28 16:19:02', 1, '19投票');
INSERT INTO `huse_participant` VALUES (18, 'AAAAAAAA', '校长', '2020-04-28 00:00:00', 1, '19投票');
INSERT INTO `huse_participant` VALUES (19, 'QWEREASD', '校长', '2019-05-24 00:00:00', 1, '19投票');
INSERT INTO `huse_participant` VALUES (20, 'ASADZXCX', '教师', '2019-04-29 10:25:56', 1, '19投票');
INSERT INTO `huse_participant` VALUES (21, 'SADASDDD', '教师', '2019-04-29 10:26:28', 1, '19投票');
INSERT INTO `huse_participant` VALUES (22, 'ZXCVVCXZ', '干部', '2019-04-29 10:26:52', 1, '19投票');
INSERT INTO `huse_participant` VALUES (23, 'ZXCVBNMC', '校长', '2019-04-29 00:00:00', 1, '19投票');

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
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of huse_score
-- ----------------------------
INSERT INTO `huse_score` VALUES (1, 'ASDFGHJK', '王干子', '19投票', '2019-04-29 15:15:04', 2, 3, 3, 3, 1);
INSERT INTO `huse_score` VALUES (2, 'AAAAAAAA', '王干子', '19投票', '2019-04-29 10:27:58', 1, 2, 4, 1, 1);
INSERT INTO `huse_score` VALUES (3, 'QWEREASD', '王干子', '19投票', '2019-04-29 10:28:25', 3, 1, 2, 1, 2);
INSERT INTO `huse_score` VALUES (4, 'ASADZXCX', '王干子', '19投票', '2019-04-29 10:28:49', 2, 1, 1, 1, 3);
INSERT INTO `huse_score` VALUES (5, 'SADASDDD', '王干子', '19投票', '2019-04-29 10:29:15', 3, 2, 1, 2, 1);
INSERT INTO `huse_score` VALUES (6, 'ZXCVVCXZ', '王干子', '19投票', '2019-04-29 10:29:40', 2, 3, 1, 2, 1);
INSERT INTO `huse_score` VALUES (7, 'ASDFGHJK', '二马亮', '19投票', '2019-04-29 10:53:14', 3, 3, 3, 3, 3);
INSERT INTO `huse_score` VALUES (8, 'AAAAAAAA', '二马亮', '19投票', '2019-04-30 15:28:49', 3, 3, 3, 3, 3);
INSERT INTO `huse_score` VALUES (9, 'QWEREASD', '二马亮', '19投票', '2019-04-29 15:29:20', 2, 2, 2, 2, 2);
INSERT INTO `huse_score` VALUES (10, 'ASADZXCX', '二马亮', '19投票', '2019-04-29 15:29:41', 2, 2, 2, 2, 2);
INSERT INTO `huse_score` VALUES (11, 'SADASDDD', '二马亮', '19投票', '2019-04-29 15:30:02', 1, 1, 1, 1, 1);

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
  `state` tinyint(1) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `alias`(`alias`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of huse_vote
-- ----------------------------
INSERT INTO `huse_vote` VALUES (4, '18民主投票', '19投票', '2019-04-28 16:03:42', '2019-05-28 00:00:00', '18民主投票', 1);
INSERT INTO `huse_vote` VALUES (5, '18民主投票', '18投票', '2019-04-28 16:03:42', '2019-05-28 00:00:00', '18民主投票', 1);
INSERT INTO `huse_vote` VALUES (6, '20民主投票', '20', '2019-04-28 16:05:32', '2019-06-28 16:05:35', '20', 1);
INSERT INTO `huse_vote` VALUES (7, '19下民主投票', '大三', '2019-04-26 00:00:00', '2019-07-28 00:00:00', '打撒大撒', 1);
INSERT INTO `huse_vote` VALUES (9, '究极无敌测试', 'ahah', '2019-04-25 00:00:00', '2019-06-28 00:00:00', '测试更改', 1);
INSERT INTO `huse_vote` VALUES (10, '盛大的撒', 'dsad', '2019-04-25 00:00:00', '2019-04-30 00:00:00', '打撒大撒', 1);
INSERT INTO `huse_vote` VALUES (11, '撕答案', 'dsa', '2019-04-10 00:00:00', '2019-05-03 00:00:00', '', 1);
INSERT INTO `huse_vote` VALUES (12, '的撒大', 'asdsa', '2019-04-19 00:00:00', '2019-06-20 00:00:00', 'sadsa', 1);
INSERT INTO `huse_vote` VALUES (13, 'dsadas', 'das', '2019-04-30 00:00:00', '2019-04-30 00:00:00', '', 1);
INSERT INTO `huse_vote` VALUES (15, '测试', 'test', '2019-04-28 20:07:26', '2020-04-28 00:00:00', '打撒大撒', 1);
INSERT INTO `huse_vote` VALUES (16, '新增一个投票', 'new', '2019-04-28 00:00:00', '2019-07-28 23:59:59', '测试而已莫慌', 1);

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
