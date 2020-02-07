USE `haiduo`;

DROP TABLE IF EXISTS `hd_article`;
CREATE TABLE `hd_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_title` varchar(255) NOT NULL DEFAULT '' COMMENT '文章标题',
  `article_img` text NOT NULL COMMENT '文章图片',
  `article_content` longtext COMMENT '文章内容',
  `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `article_author` varchar(20) NOT NULL DEFAULT '' COMMENT '发布作者',
  `article_readnum` int(11) NOT NULL DEFAULT '0' COMMENT '阅读量',
  `article_likenum` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `article_keyword` varchar(255) NOT NULL DEFAULT '' COMMENT '页面关键字',
  `article_state` int(1) NOT NULL DEFAULT '0' COMMENT '是否显示',
  PRIMARY KEY (`id`),
  KEY `idx_article_title` (`article_title`),
  KEY `idx_article_content` (`article_content`(10)),
  KEY `idx_article_keyword` (`article_keyword`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';

DROP TABLE IF EXISTS `hd_article_log`;
CREATE TABLE `hd_article_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
   `userid` varchar(255) NOT NULL DEFAULT '' COMMENT '用户id',
  `aid` int(11) NOT NULL DEFAULT '0' COMMENT '文章id',
  `read` int(11) NOT NULL DEFAULT '0' COMMENT '阅读',
  `like` int(11) NOT NULL DEFAULT '0' COMMENT '点赞',
   `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `idx_aid` (`aid`),
  KEY `idx_userid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点赞/阅读记录';

DROP TABLE IF EXISTS `hd_doctor`;
CREATE TABLE `hd_doctor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(255) NOT NULL DEFAULT '' COMMENT '科室',
  `hospital` varchar(255) NOT NULL DEFAULT '' COMMENT '医院',
  `level` varchar(255) NOT NULL DEFAULT '' COMMENT '级别',
  `goodat` varchar(255) NOT NULL DEFAULT '' COMMENT '擅长',
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `realname` varchar(20) NOT NULL DEFAULT '' COMMENT '真实名字',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `isblack` int(11) NOT NULL DEFAULT '0' COMMENT '是否加入黑名单',
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `mobileverify` tinyint(3) NOT NULL DEFAULT '0' COMMENT '手机是否验证',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `content` text NOT NULL COMMENT '简介',
  `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `birthyear` varchar(255) NOT NULL DEFAULT '' COMMENT '出生年份',
  `birthmonth` varchar(255) NOT NULL DEFAULT '' COMMENT '出生月份',
  `birthday` varchar(255) NOT NULL DEFAULT '' COMMENT '出生日期',
  `gender` tinyint(3) NOT NULL DEFAULT '0' COMMENT '性别',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `province` varchar(255) NOT NULL DEFAULT '' COMMENT '省',
  `city` varchar(255) NOT NULL DEFAULT '' COMMENT '市',
  `area` varchar(255) NOT NULL DEFAULT '' COMMENT '区',
  PRIMARY KEY (`id`),
  KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生';

DROP TABLE IF EXISTS `hd_user`;
CREATE TABLE `hd_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `realname` varchar(20) NOT NULL DEFAULT '' COMMENT '真实名字',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `isblack` int(11) NOT NULL DEFAULT '0' COMMENT '是否加入黑名单',
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `mobileverify` tinyint(3) NOT NULL DEFAULT '0' COMMENT '手机是否验证',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `content` text NOT NULL COMMENT '简介',
  `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `birthyear` varchar(255) NOT NULL DEFAULT '' COMMENT '出生年份',
  `birthmonth` varchar(255) NOT NULL DEFAULT '' COMMENT '出生月份',
  `birthday` varchar(255) NOT NULL DEFAULT '' COMMENT '出生日期',
  `gender` tinyint(3) NOT NULL DEFAULT '0' COMMENT '性别',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `province` varchar(255) NOT NULL DEFAULT '' COMMENT '省',
  `city` varchar(255) NOT NULL DEFAULT '' COMMENT '市',
  `area` varchar(255) NOT NULL DEFAULT '' COMMENT '区',
  PRIMARY KEY (`id`),
  KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

DROP TABLE IF EXISTS `hd_user_address`;
CREATE TABLE `hd_user_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `realname` varchar(20) NOT NULL DEFAULT '' COMMENT '真实名字',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `province` varchar(30) NOT NULL DEFAULT '' COMMENT '省',
  `city` varchar(30) NOT NULL DEFAULT '' COMMENT '市',
  `area` varchar(30) NOT NULL DEFAULT '' COMMENT '区',
  `address` varchar(300) NOT NULL DEFAULT '' COMMENT '详细地址',
  `isdefault` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为默认地址',
  `zipcode` varchar(255) NOT NULL DEFAULT '' COMMENT '邮政编码',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `street` varchar(50) NOT NULL DEFAULT '' COMMENT '街道',
  `lng` varchar(255) NOT NULL DEFAULT '' COMMENT '经度',
  `lat` varchar(255) NOT NULL DEFAULT '' COMMENT '纬度',
    `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `idx_userid` (`userid`),
  KEY `idx_isdefault` (`isdefault`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='用户地址';

DROP TABLE IF EXISTS `hd_video`;
CREATE TABLE `hd_video` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `doctorid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '医生id',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '标题',
  `video_img` varchar(255) NOT NULL DEFAULT '' COMMENT '视频图片',
  `video_url` varchar(255) NOT NULL DEFAULT '' COMMENT '视频地址',
  `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频';