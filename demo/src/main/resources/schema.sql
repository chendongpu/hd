USE `haiduo`;


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
  `isdoctor` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否为医生',
  `department` varchar(255) NOT NULL DEFAULT '' COMMENT '科室',
  `hospital` varchar(255) NOT NULL DEFAULT '' COMMENT '医院',
  `level` varchar(255) NOT NULL DEFAULT '' COMMENT '级别',
  `goodat` varchar(255) NOT NULL DEFAULT '' COMMENT '擅长',
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

DROP TABLE IF EXISTS `hd_user_article`;
CREATE TABLE `hd_user_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) NOT NULL DEFAULT '' COMMENT '用户id',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '文章标题',
  `img` text NOT NULL COMMENT '文章图片',
  `content` longtext COMMENT '文章内容',
  `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `author` varchar(20) NOT NULL DEFAULT '' COMMENT '发布作者',
  `readnum` int(11) NOT NULL DEFAULT '0' COMMENT '阅读量',
  `likenum` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `keyword` varchar(255) NOT NULL DEFAULT '' COMMENT '页面关键字',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '是否显示',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_content` (`content`(10)),
  KEY `idx_keyword` (`keyword`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';


DROP TABLE IF EXISTS `hd_user_article_read`;
CREATE TABLE `hd_user_article_read` (
  `userid` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `aid` int(11) NOT NULL DEFAULT '0' COMMENT '文章id',
  PRIMARY KEY (`userid`,`aid`),
  KEY `idx_userid` (`userid`),
  KEY `idx_aid` (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅读记录';

DROP TABLE IF EXISTS `hd_user_video`;
CREATE TABLE `hd_user_video` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '标题',
  `img` varchar(255) NOT NULL DEFAULT '' COMMENT '视频图片',
  `video` varchar(255) NOT NULL DEFAULT '' COMMENT '视频地址',
  `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '是否显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频';

DROP TABLE IF EXISTS `hd_user_concern`;
CREATE TABLE `hd_user_concern` (
  `userid` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `concernid` int(11) NOT NULL DEFAULT '0' COMMENT '被关注用户id',
  PRIMARY KEY (`userid`,`concernid`),
  KEY `idx_userid` (`userid`),
  KEY `idx_concernid` (`concernid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关注表';

DROP TABLE IF EXISTS `hd_user_article_collect`;
CREATE TABLE `hd_user_article_collect` (
  `userid` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `aid` int(11) NOT NULL DEFAULT '0' COMMENT '文章id',
  PRIMARY KEY (`userid`,`aid`),
  KEY `idx_userid` (`userid`),
  KEY `idx_aid` (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户收藏文章表';




DROP TABLE IF EXISTS `hd_user_test`;
CREATE TABLE `hd_user_test` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `userid` INT(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `title` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '测评标题',
  `content` LONGTEXT COMMENT '测评说明',
  `createtime` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `state` INT(1) NOT NULL DEFAULT '0' COMMENT '是否审核通过',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_content` (`content`(10))
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='医生测评';

DROP TABLE IF EXISTS `hd_user_test_question`;
CREATE TABLE `hd_user_test_question` (
  `testid` INT(11) NOT NULL DEFAULT '0'  COMMENT '测评id',
  `questionid` INT(11) NOT NULL DEFAULT '0' COMMENT '问题id',
    PRIMARY KEY (`testid`,`questionid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='测评问题';

DROP TABLE IF EXISTS `hd_user_question`;
CREATE TABLE `hd_user_question` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `userid` INT(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `title` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '问题',
   `type` int(1) NOT NULL DEFAULT '0' COMMENT '0 单选 1多选 2填空',
   `score` INT(11) NOT NULL DEFAULT '0' COMMENT '得分',
    `createtime` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户问题';

DROP TABLE IF EXISTS `hd_user_question_choice`;
CREATE TABLE `hd_user_question_choice` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `questionid` INT(11) NOT NULL DEFAULT '0' COMMENT '问题id',
  `choice` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '问题选项',
  PRIMARY KEY (`id`),
  KEY `idx_choice` (`choice`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='问题选项';

DROP TABLE IF EXISTS `hd_user_question_answer`;
CREATE TABLE `hd_user_question_answer` (
  `questionid` INT(11) NOT NULL DEFAULT '0' COMMENT '问题id',
  `choiceid` INT(11) NOT NULL DEFAULT '0'  COMMENT '选项id',
    PRIMARY KEY (`questionid`,`choiceid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='问题答案';

DROP TABLE IF EXISTS `hd_user_test_log`;
CREATE TABLE `hd_user_test_log` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `userid` INT(11) NOT NULL DEFAULT '0' COMMENT '用户id',
    `testid` INT(11) NOT NULL DEFAULT '0' COMMENT '测评id',
    `title` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '测评标题',
    `content` LONGTEXT COMMENT '测评说明',
    `createtime` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
     `result` LONGTEXT COMMENT '测评结果',
     PRIMARY KEY (`id`),
     KEY `idx_title` (`title`),
     KEY `idx_content` (`content`(10))
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='测评记录';

DROP TABLE IF EXISTS `hd_point_task`;
CREATE TABLE `hd_point_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '任务名称',
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '0 签到 1开直播 2分享',
  `point` int(11) NOT NULL DEFAULT '0' COMMENT '奖励积分数量',
  `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '是否审核通过',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务';

DROP TABLE IF EXISTS `hd_user_msg`;
CREATE TABLE `hd_user_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `msg` varchar(20) NOT NULL DEFAULT '' COMMENT '消息',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '类型 0系统通知 1评论 2 点赞',
  `otherid` int(11) NOT NULL DEFAULT '0' COMMENT '评论或者点赞用户id',
  `isread` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已读',
  `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `idx_userid` (`userid`),
  KEY `idx_isread` (`isread`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='用户消息记录';

DROP TABLE IF EXISTS `hd_user_task_log`;
CREATE TABLE `hd_user_task_log` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `userid` INT(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `taskid` INT(11) NOT NULL DEFAULT '0' COMMENT '任务id',
  `title` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '任务名称',
  `point` INT(11) NOT NULL DEFAULT '0' COMMENT '奖励积分数量',
  `createtime` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `idx_userid` (`userid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户完成任务记录';





