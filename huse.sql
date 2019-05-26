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

 Date: 26/05/2019 21:45:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cadre_info
-- ----------------------------
DROP TABLE IF EXISTS `cadre_info`;
CREATE TABLE `cadre_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `header_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件储存的路径',
  `time` datetime NULL DEFAULT NULL COMMENT '保存的时间',
  `card_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外键干部名',
  `info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '个人信息',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cadre_info_ibfk_1`(`card_name`) USING BTREE,
  CONSTRAINT `cadre_info_ibfk_1` FOREIGN KEY (`card_name`) REFERENCES `huse_cadre` (`cadre_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of cadre_info
-- ----------------------------
INSERT INTO `cadre_info` VALUES (1, 'xxxx', '0f6649c0.png', '0619937e.doc', '2019-05-07 18:09:12', '王子', '哥哥就结案结案\r\n@Configuration\r\npublic class ShiroConfig {\r\n \r\n    private final static Logger logger = LoggerFactory.getLogger(ShiroConfig.class);\r\n \r\n    // 下面两个方法对 注解权限起作用有很大的关系，请把这两个方法，放在配置的最上面\r\n    @Bean(name = \"lifecycleBeanPostProcessor\")\r\n    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {\r\n        return new LifecycleBeanPostProcessor();\r\n    }\r\n    @Bean\r\n    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {\r\n        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();\r\n        autoProxyCreator.setProxyTargetClass(true);\r\n        return autoProxyCreator;\r\n    }\r\n \r\n    //将自己的验证方式加入容器\r\n    @Bean\r\n    public MyRealm myRealm() {\r\n        System.out.println( \"注入 realm\" );\r\n        MyRealm myRealm = new MyRealm();\r\n        return myRealm;\r\n    }\r\n \r\n    // 配置sessionDAO\r\n    @Bean(name=\"sessionDAO\")\r\n    public MemorySessionDAO getMemorySessionDAO(){\r\n        MemorySessionDAO sessionDAO = new MemorySessionDAO();\r\n        return sessionDAO;\r\n    }\r\n \r\n    //配置shiro session 的一个管理器\r\n    @Bean(name = \"sessionManager\")\r\n    public DefaultWebSessionManager getDefaultWebSessionManager(){\r\n        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();\r\n        // 设置session过期时间\r\n        sessionManager.setGlobalSessionTimeout(60*60*1000);\r\n        // 请注意看代码\r\n        sessionManager.setSessionDAO(getMemorySessionDAO());\r\n        return sessionManager;\r\n    }\r\n \r\n    @Bean(name = \"securityManager\")\r\n    public DefaultWebSecurityManager getDefaultWebSecurityManager() {\r\n        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();\r\n        defaultWebSecurityManager.setRealm( myRealm() );\r\n        // 将sessionDAO放进来\r\n        defaultWebSecurityManager.setSessionManager( getDefaultWebSessionManager() );\r\n        return defaultWebSecurityManager;\r\n    }\r\n \r\n    @Bean\r\n    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(\r\n            DefaultWebSecurityManager securityManager) {\r\n        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();\r\n        advisor.setSecurityManager(securityManager);\r\n        return advisor;\r\n    }\r\n \r\n    //Filter工厂，设置对应的过滤条件和跳转条件\r\n    @Bean\r\n    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {\r\n        System.out.println( \"shiro 过滤器\" );\r\n        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();\r\n        shiroFilterFactoryBean.setSecurityManager(securityManager);\r\n \r\n        //拦截器.\r\n        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();\r\n \r\n        // 配置不会被拦截的链接 顺序判断\r\n        filterChainDefinitionMap.put(\"/static/**\", \"anon\");\r\n        filterChainDefinitionMap.put(\"/login\", \"anon\");\r\n        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了\r\n        filterChainDefinitionMap.put(\"/logout\", \"logout\");\r\n        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;\r\n        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->\r\n        filterChainDefinitionMap.put(\"/**\", \"authc\");\r\n        // 如果不设置默认会自动寻找Web工程根目录下的\"/login.jsp\"页面\r\n        shiroFilterFactoryBean.setLoginUrl(\"/login.html\");\r\n        // 登录成功后要跳转的链接\r\n        shiroFilterFactoryBean.setSuccessUrl(\"/index.html\");\r\n \r\n        //未授权界面;\r\n        shiroFilterFactoryBean.setUnauthorizedUrl(\"/403.html\");\r\n        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);\r\n        return shiroFilterFactoryBean;\r\n    }\r\n \r\n}');
INSERT INTO `cadre_info` VALUES (5, '三马亮', '1890d41f.jpg', 'a6f3643c.docx', '2019-05-14 20:04:26', '二马亮', '自古以来，中国就是礼仪之邦，交往讲究礼尚往来，交锋讲究先礼后兵。\r\n\r\n　　对中美经贸摩擦，中方始终保持极大的克制，始终抱以极大的诚意，认真精心准备谈判。即便在美方进行极限施压，宣布对2000亿美元的中国输美商品加征关税后，中方仍派代表团赴美进行第十一轮高级别磋商，把通过谈判解决问题的诚意保持到最后一刻。在美方此举直接导致中美经贸摩擦升级后，中方不得不对原产于美国的部分进口商品调整加征关税措施，同时仍希望美方回到双边经贸磋商的正确轨道，和中方共同努力，相向而行，在平等相待、互相尊重的基础上继续推进谈判。\r\n\r\n　　君子之国，有君子之道。正如有外国政要指出的，中国在国际上重信守诺，体现了古老的中华文明的智慧和中国作为一个负责任大国的历史担当。一年来，中方推动谈判的诚意和善意有目共睹。我们重信用、守承诺，这一点从来没有改变过。遗憾的是，美方一再提高要价，几番出尔反尔，痴迷于极限施压，以为可以通过这种不讲信用、蛮横霸凌的做法实现自己利益最大化，这是判错了形势、认错了对象！千万别把中国的诚意当可欺，千万别以为中方在重大原则问题上会退让妥协，千万别以为中方会拿国家核心利益和人民根本利益去做交易。中国人民维护国家利益和尊严的信念高度一致、决心坚如磐石，现在的中国绝不会再做丧权辱国的事情，任何人都不要指望中国会吞下损害自己核心利益的苦果。搞霸凌主义、极限施压那一套，除了有损美方信誉和形象，别无他用。\r\n\r\n　　面对美方挑起的经贸摩擦，中方的立场始终明确：贸易战没有赢家，中国不想打，但也不怕打。中国经济和美国经济深度融合，美方加征关税，对中国人民不利，对美国人民不利，对世界人民也不利。从一开始，中方对此就有清醒的认识，始终以最大的诚意与美方磋商，做到了仁至义尽。同时，中方对最坏的结果也有预判，做好了充足的准备。中国人民从历史经验中懂得，不打无准备之仗，不打无把握之仗，必须坚持底线思维，从最坏处准备，向最好处努力。一年来，举国上下围绕稳就业、稳金融、稳外贸、稳外资、稳投资、稳预期做好各方面工作，成效正在显现，经济基本面稳中向好，我们对中国经济的承压能力、抗风险能力有足够自信，无惧美方把贸易战打下去。\r\n\r\n　　中美开展经贸合作是最好的选择，但合作是有原则的。在重大原则问题上中方不会让步，也不可能让步。中方谈判的大门始终是敞开的，始终认为中美在经贸领域有着广泛的共同利益和广阔的合作空间，应该求同存异，合作共赢。但谈判必须有诚意，必须遵循相互尊重、平等相待的原则，双方的协议必须是平等、互利的。只有双方相向而行，求同存异、聚同化异，抱着理性、务实的态度解决问题，才能让中美经贸交往更好造福两国人民和世界人民。');
INSERT INTO `cadre_info` VALUES (6, NULL, '59289da7.jpg', '7877eb2b.doc', '2019-05-15 17:55:19', '杠子健', '我真牛');
INSERT INTO `cadre_info` VALUES (7, NULL, NULL, NULL, '2019-05-15 18:07:10', 'wk', '我很牛逼的你不要惹我');
INSERT INTO `cadre_info` VALUES (9, NULL, '6db4e8fa.jpg', '6b5244a5.docx', '2019-05-21 17:56:02', 'mmw', '你对真正的力量一无所知\r\n\r\n脚本是非常方便的工具，你能想到的任何重复性的工作都能用脚本来执行\r\n\r\n以前写过的有\r\n\r\n测试工具和内容自动化\r\nlog监视，服务死活监视\r\n邮件自动发送，服务自动重启\r\n语法都不难，难的是要灵活对应需求的变化');

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
INSERT INTO `huse_admin` VALUES (1, 'superuser', 'superuser', '13574654443', 1, '超级管理员', 'su');
INSERT INTO `huse_admin` VALUES (2, 'user', '1234', '15874677727', 1, '普通用户', 'su');
INSERT INTO `huse_admin` VALUES (3, 'fl', '11111', '15874677739', 0, '冯亮包夜开黑的样子帅的一批', 'su');
INSERT INTO `huse_admin` VALUES (4, 'zjj', 'jj123', '15200977801', 1, '冯亮的兄弟张jiji', 'user');
INSERT INTO `huse_admin` VALUES (5, 'qd', '123', '18222222222', 1, '冯亮兄弟之缺德', 'user');
INSERT INTO `huse_admin` VALUES (7, 'xb', 'xbbb', '13967421654', 1, '冯亮兄弟之小白', 'user');
INSERT INTO `huse_admin` VALUES (8, 'lx', 'lx888', '15844489773', 1, '小帅哥雷新', 'user');
INSERT INTO `huse_admin` VALUES (9, 'wzx', 'wzx', '13599384211', 1, '王子小帅哥', 'user');
INSERT INTO `huse_admin` VALUES (21, '李子健', 'shabi', '15688887724', 1, '撒上大声地撒傻逼', 'user');
INSERT INTO `huse_admin` VALUES (24, 'sbs', 'dsadas', '15707472222', 1, '打撒大撒', 'user');

-- ----------------------------
-- Table structure for huse_cadre
-- ----------------------------
DROP TABLE IF EXISTS `huse_cadre`;
CREATE TABLE `huse_cadre`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cadre_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '干部姓名,唯一',
  `job` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位',
  `state` tinyint(1) NOT NULL COMMENT '启用状态',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `password` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role` enum('干部') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色',
  `avote_lias` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参与的投票项目',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `cadre_name_2`(`cadre_name`) USING BTREE,
  INDEX `cadre_name`(`cadre_name`) USING BTREE,
  INDEX `avote_lias`(`avote_lias`) USING BTREE,
  CONSTRAINT `huse_cadre_ibfk_1` FOREIGN KEY (`avote_lias`) REFERENCES `huse_vote` (`alias`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of huse_cadre
-- ----------------------------
INSERT INTO `huse_cadre` VALUES (1, '王子', '贼帅', 0, '二马亮打呼噜', 'ssss', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (2, '二马亮', '网吧经理', 1, '他是网吧经理', '2222', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (4, '杠子健', '网吧试喷员', 1, '傻吊一个', '3333', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (5, 'wk', '凯特威公子', 1, '牛逼', 'wkkk', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (6, 'zjj', 'hotel男神', 0, '你知道加州招待所吗', 'adsad', '干部', '18投票');
INSERT INTO `huse_cadre` VALUES (7, '啦哈哈哈', '学习小霸王', 0, 'java牛逼', 'study', '干部', '18投票');
INSERT INTO `huse_cadre` VALUES (8, 'mmw', '富两代', 1, '虽然我很有钱但是我也爱学习,考托福的男人', 'rich', '干部', '18投票');
INSERT INTO `huse_cadre` VALUES (19, '假乃亮', '绿帽社社长', 1, 'PGone他是我弟弟', 'jll', '干部', '18投票');
INSERT INTO `huse_cadre` VALUES (23, '张二毛', '战忽局部长', 1, '印度流批', '12222', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (32, 'DMW', '厉害', 1, '打撒大撒', '2016060', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (40, '瞎编的', '打摆子', 1, '测试推马云', '123456', '干部', '测试就推码云');
INSERT INTO `huse_cadre` VALUES (43, '王三麻', '厂长', 0, '爱学习哟', 'gd888', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (44, '杨洋洋', '厂长的表哥', 0, '我系团支书', 'bg250', '干部', '19投票');
INSERT INTO `huse_cadre` VALUES (45, '小飞象', '仓库管理员', 1, '我就是仓库管理员呀', 'asdfghjkl', '干部', 'ԅ(¯㉨¯ԅ)');

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
  `role` enum('校领导','干部','教师','群众') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参与者类型',
  `endtime` datetime NOT NULL COMMENT '有效截止时间',
  `state` tinyint(1) NOT NULL COMMENT '是否投票',
  `vote_alias` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投票别名',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PIN_2`(`PIN`) USING BTREE,
  INDEX `PIN`(`PIN`) USING BTREE,
  INDEX `vote_alias`(`vote_alias`) USING BTREE,
  CONSTRAINT `huse_participant_ibfk_1` FOREIGN KEY (`vote_alias`) REFERENCES `huse_vote` (`alias`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of huse_participant
-- ----------------------------
INSERT INTO `huse_participant` VALUES (17, 'ASDFGHJK', '校领导', '2019-06-08 16:19:02', 1, '19投票');
INSERT INTO `huse_participant` VALUES (18, 'AAAAAAAA', '校领导', '2020-04-28 00:00:00', 1, '19投票');
INSERT INTO `huse_participant` VALUES (19, 'QWEREASD', '校领导', '2019-05-24 00:00:00', 1, '19投票');
INSERT INTO `huse_participant` VALUES (20, 'ASADZXCX', '教师', '2019-04-29 10:25:56', 1, '19投票');
INSERT INTO `huse_participant` VALUES (21, 'SADASDDD', '教师', '2019-04-29 10:26:28', 1, '19投票');
INSERT INTO `huse_participant` VALUES (22, 'ZXCVVCXZ', '干部', '2019-04-29 10:26:52', 1, '19投票');
INSERT INTO `huse_participant` VALUES (38, 'ASDFGHZZ', '校领导', '2019-05-17 17:34:08', 1, '19投票');
INSERT INTO `huse_participant` VALUES (39, 'PUYGFGD', '校领导', '2019-06-05 00:00:00', 1, '19投票');
INSERT INTO `huse_participant` VALUES (40, 'PPDDDDPP', '干部', '2019-06-06 00:00:00', 0, '19投票');
INSERT INTO `huse_participant` VALUES (41, 'ZZZZZZZZ', '校领导', '2019-05-31 20:36:45', 1, 'ԅ(¯㉨¯ԅ)');

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
  `state` tinyint(1) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `alias`(`alias`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of huse_vote
-- ----------------------------
INSERT INTO `huse_vote` VALUES (4, '19民主投票', '19投票', '2019-04-28 16:03:42', '2019-05-28 00:00:00', '18民主投票', 1);
INSERT INTO `huse_vote` VALUES (5, '18民主投票', '18投票', '2019-04-28 16:03:42', '2019-05-28 00:00:00', '18民主投票', 1);
INSERT INTO `huse_vote` VALUES (7, '50下民主投票', '大三', '2019-04-26 00:00:00', '2019-07-28 00:00:00', '打撒大撒', 1);
INSERT INTO `huse_vote` VALUES (9, '究极无敌测试', 'ahah', '2019-04-25 00:00:00', '2019-06-28 00:00:00', '测试更改', 1);
INSERT INTO `huse_vote` VALUES (10, '盛大的撒', 'dsad', '2019-04-25 00:00:00', '2019-04-30 00:00:00', '打撒大撒', 1);
INSERT INTO `huse_vote` VALUES (11, '撕答案', 'dsa', '2019-04-10 00:00:00', '2019-05-03 00:00:00', '', 1);
INSERT INTO `huse_vote` VALUES (12, '的撒大', 'asdsa', '2019-04-19 00:00:00', '2019-06-20 00:00:00', 'sadsa', 1);
INSERT INTO `huse_vote` VALUES (13, 'dsadas', 'das', '2019-04-30 00:00:00', '2019-04-30 00:00:00', '', 1);
INSERT INTO `huse_vote` VALUES (16, '新增一个投票', 'new', '2019-04-28 00:00:00', '2019-07-28 23:59:59', '测试而已莫慌', 0);
INSERT INTO `huse_vote` VALUES (17, '今晚测试', '测试就推码云', '2019-05-10 22:00:52', '2019-05-23 00:00:00', '12345', 1);
INSERT INTO `huse_vote` VALUES (18, '再来一个测试a', 'ԅ(¯㉨¯ԅ)', '2021-05-10 00:00:00', '2019-05-30 00:00:00', '哈哈', 1);

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
