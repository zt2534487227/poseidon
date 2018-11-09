
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `digest` varchar(400) DEFAULT NULL COMMENT '博客摘要',
  `content` text COMMENT '文章内容',
  `categoryId` int(10) NOT NULL COMMENT '分类id',
  `tags` varchar(255) DEFAULT NULL COMMENT '标签（多个用","分隔）',
  `publishTime` datetime DEFAULT NULL COMMENT '发布时间',
  `userId` int(10) NOT NULL COMMENT '用户id',
  `userName` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `clickHit` int(10) DEFAULT '0' COMMENT '点击数',
  `replyHit` int(10) DEFAULT '0' COMMENT '评论数',
  `showMode` int(1) NOT NULL DEFAULT '1' COMMENT '文章模式（1：公开，2：私有）',
  `articleStatus` int(1) NOT NULL DEFAULT '0' COMMENT '状态（0：编辑中；1：已发布）',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `version` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';


DROP TABLE IF EXISTS `t_attach`;
CREATE TABLE `t_attach` (
  `id` varchar(36) NOT NULL COMMENT '主键，uuid',
  `fileName` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `filePath` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `fileSize` decimal(10,2) DEFAULT NULL COMMENT '文件大小',
  `fileType` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `version` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `cName` varchar(100) NOT NULL COMMENT '分类名称',
  `showIndex` int(3) DEFAULT NULL COMMENT '排序',
  `categoryStatus` int(1) NOT NULL DEFAULT '1' COMMENT '状态（1：有效，2：无效）',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `version` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='分类表';

INSERT INTO `t_category` VALUES ('1', 'Java', '1', '1', '2018-10-09 20:27:51', '2018-10-09 20:27:51');
INSERT INTO `t_category` VALUES ('2', 'linux', '2', '1', '2018-10-09 20:28:29', '2018-10-09 20:28:29');
INSERT INTO `t_category` VALUES ('3', 'js', '3', '1', '2018-10-09 20:29:32', '2018-10-09 20:29:32');

DROP TABLE IF EXISTS `t_collection`;
CREATE TABLE `t_collection` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `userId` int(10) NOT NULL COMMENT '用户id，user表id',
  `articleTitle` varchar(50) NOT NULL COMMENT '文章标题',
  `articleId` int(10) NOT NULL COMMENT '文章id',
  `articleAuthor` varchar(255) NOT NULL,
  `createTime` datetime NOT NULL COMMENT '收藏日期',
  `version` datetime NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的收藏';


DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `userName` varchar(50) NOT NULL COMMENT '评论者昵称',
  `userId` int(10) DEFAULT NULL COMMENT '用户id',
  `content` varchar(255) NOT NULL COMMENT '评论的内容',
  `articleId` int(10) DEFAULT NULL COMMENT '文章id',
  `bloggerId` int(10) NOT NULL COMMENT '博主id',
  `parentId` int(10) DEFAULT NULL COMMENT '父级评论id',
  `treePath` varchar(255) DEFAULT NULL COMMENT '树形路径（如：0,1,2）',
  `commentTime` varchar(14) DEFAULT NULL COMMENT '评论时间',
  `isShow` int(1) DEFAULT '1' COMMENT '是否展示（1：是，2：否）',
  `commentStatus` int(1) DEFAULT NULL COMMENT '状态',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `version` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';


DROP TABLE IF EXISTS `t_concern`;
CREATE TABLE `t_concern` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `concernName` varchar(255) NOT NULL COMMENT '关注的用户昵称',
  `concernId` int(11) NOT NULL COMMENT '关注的用户id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '关注时间',
  `version` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的关注';


DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(10) NOT NULL,
  `menuName` varchar(50) NOT NULL COMMENT '菜单名称',
  `showIndex` int(6) DEFAULT NULL COMMENT '排序',
  `parentId` int(11) DEFAULT '0',
  `treePath` varchar(255) DEFAULT NULL,
  `menuStatus` int(1) NOT NULL DEFAULT '1' COMMENT '菜单状态（0：停用，1：启用）',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `version` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_menu` VALUES ('1', '网站首页', '1', '0', '0,1', '1', '2018-09-18 14:16:53', '2018-09-18 14:16:55');
INSERT INTO `t_menu` VALUES ('2', '技术杂谈', '2', '0', '0,2', '1', '2018-09-18 11:37:09', '2018-09-18 11:37:11');
INSERT INTO `t_menu` VALUES ('3', '生活笔记', '3', '0', '0,3', '1', '2018-09-18 14:16:58', '2018-09-18 14:17:00');
INSERT INTO `t_menu` VALUES ('4', '资源共享', '4', '0', '0,4', '1', '2018-09-18 14:17:03', '2018-09-18 14:17:06');
INSERT INTO `t_menu` VALUES ('5', '留言板', '5', '0', '0,5', '1', '2018-09-18 14:17:09', '2018-09-18 14:17:11');
INSERT INTO `t_menu` VALUES ('6', '关于我', '6', '0', '0,6', '1', '2018-09-18 14:17:16', '2018-09-18 14:17:19');

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `userAccount` varchar(50) NOT NULL COMMENT '用户账号，唯一',
  `roleType` int(1) DEFAULT '2' COMMENT '角色类型（1:管理员，2:普通用户）',
  `nickName` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) DEFAULT NULL COMMENT '密码  MD5(MD5(密码)+校验码)',
  `checkMode` int(1) DEFAULT '1' COMMENT '校验方式(1：密码，2：手机验证码)',
  `checkCode` char(6) DEFAULT NULL COMMENT '校验码',
  `realName` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `sex` int(1) DEFAULT NULL COMMENT '性别（1：男，2：女）',
  `userImage` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `mobilePhone` char(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `sign` varchar(255) DEFAULT NULL COMMENT '个性签名',
  `info` text COMMENT '个人简介',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `areaId` int(10) DEFAULT NULL COMMENT '地区id',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `wxId` varchar(255) DEFAULT NULL COMMENT '微信标识',
  `qqId` varchar(255) DEFAULT NULL COMMENT 'qq标识',
  `sinaId` varchar(255) DEFAULT NULL COMMENT '新浪标识',
  `userStatus` int(1) NOT NULL DEFAULT '1' COMMENT '用户状态（1：正常，2：审核中，3：冻结中，4：已注销）',
  `statusMemo` varchar(255) DEFAULT NULL COMMENT '状态说明',
  `loginIp` varchar(255) DEFAULT NULL COMMENT '登录ip',
  `loginTime` varchar(14) DEFAULT NULL COMMENT '登录日期',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `version` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

DROP TABLE IF EXISTS `t_user_token`;
CREATE TABLE `t_user_token` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `token` varchar(255) NOT NULL COMMENT 'token',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `expireTime` datetime NOT NULL COMMENT '失效时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
