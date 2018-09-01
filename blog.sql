/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-09-01 10:32:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `desc` varchar(400) DEFAULT NULL COMMENT '博客摘要',
  `content` text COMMENT '文章内容',
  `categoryId` int(10) NOT NULL COMMENT '分类id',
  `tags` varchar(255) DEFAULT NULL COMMENT '标签（多个用","分隔）',
  `publishTime` varchar(14) DEFAULT NULL COMMENT '发布时间',
  `userId` int(10) NOT NULL COMMENT '用户id',
  `ckickHit` int(10) DEFAULT NULL COMMENT '点击数',
  `replyHit` int(10) DEFAULT NULL COMMENT '评论数',
  `showMode` int(1) DEFAULT NULL COMMENT '文章模式（1：公开，2：私有）',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `creatTime` datetime DEFAULT NULL COMMENT '创建时间',
  `version` datetime DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `name` varchar(100) NOT NULL COMMENT '分类名称',
  `index` int(3) DEFAULT NULL COMMENT '排序',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态（1：有效，2：无效）',
  `creatTime` datetime DEFAULT NULL COMMENT '创建时间',
  `version` datetime DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分类表';

-- ----------------------------
-- Table structure for t_collection
-- ----------------------------
DROP TABLE IF EXISTS `t_collection`;
CREATE TABLE `t_collection` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `userId` int(10) DEFAULT NULL COMMENT '用户id，user表id',
  `articleTitle` varchar(50) DEFAULT NULL COMMENT '文章标题',
  `articleId` varchar(255) DEFAULT NULL COMMENT '文章id',
  `createTime` datetime DEFAULT NULL COMMENT '收藏日期',
  `version` datetime DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的收藏';

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
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
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `version` datetime DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Table structure for t_concern
-- ----------------------------
DROP TABLE IF EXISTS `t_concern`;
CREATE TABLE `t_concern` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `userId` int(11) DEFAULT NULL COMMENT '用户id',
  `concernName` varchar(255) DEFAULT NULL COMMENT '关注的用户昵称',
  `concernId` int(11) DEFAULT NULL COMMENT '关注的用户id',
  `createTime` datetime DEFAULT NULL COMMENT '关注时间',
  `version` datetime DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的关注';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `userAccount` varchar(50) NOT NULL COMMENT '用户账号，唯一',
  `roleType` int(1) DEFAULT NULL COMMENT '角色类型（管理员，普通用户）',
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
  `userStatus` int(1) NOT NULL DEFAULT '2' COMMENT '用户状态（1：正常，2：审核中，3：冻结中，4：已注销）',
  `statusMemo` varchar(255) DEFAULT NULL COMMENT '状态说明',
  `loginIp` varchar(255) DEFAULT NULL COMMENT '登录ip',
  `loginTime` varchar(14) DEFAULT NULL COMMENT '登录日期',
  `creatTime` datetime NOT NULL COMMENT '创建时间',
  `version` datetime NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
