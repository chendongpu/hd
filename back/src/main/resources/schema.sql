USE `test`;


DROP TABLE IF EXISTS `hd_admin`;
CREATE TABLE `hd_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员';


DROP TABLE IF EXISTS `hd_doctor_department`;
CREATE TABLE `hd_doctor_department` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '科室名字',
  `img` varchar(255) NOT NULL DEFAULT '' COMMENT '科室图片',
  `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生科室';


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
  `department` int(11) NOT NULL DEFAULT '0' COMMENT '科室',
  `hospital` varchar(255) NOT NULL DEFAULT '' COMMENT '医院',
  `level` varchar(255) NOT NULL DEFAULT '' COMMENT '级别',
  `goodat` varchar(255) NOT NULL DEFAULT '' COMMENT '擅长',
  `money` bigint(20) NOT NULL DEFAULT '0' COMMENT '诊金',
  `duration` int(11) NOT NULL DEFAULT '0' COMMENT '时长/分',
  `departmenttel` varchar(255) NOT NULL DEFAULT '' COMMENT '科室电话',
  `account` bigint(20) NOT NULL DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`),
  KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

DROP TABLE IF EXISTS `hd_treatment_order`;
CREATE TABLE `hd_treatment_order` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `orderid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '订单id',
  `userid` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '用户id',
  `doctorid` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '医生id',
  `money` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '诊金',
  `duration` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '时长',
  `createtime` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `begintime` INT(20) NOT NULL DEFAULT '0' COMMENT '开始时间',
  `endtime` INT(20) NOT NULL DEFAULT '0' COMMENT '结束时间',
  `state` INT(1) NOT NULL DEFAULT '0' COMMENT '0 未支付 1已支付 2已结束 3已评价',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='接诊订单';



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
  `type` tinyint(1) DEFAULT '0' COMMENT '文章类型 0文章 1视频',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '文章标题',
  `img` text NOT NULL COMMENT '文章图片',
  `content` longtext COMMENT '文章内容/视频',
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


DROP TABLE IF EXISTS `hd_user_test_report`;
CREATE TABLE `hd_user_test_report` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `testid` INT(11) NOT NULL DEFAULT '0' COMMENT '评估id',
  `score` INT(11) NOT NULL DEFAULT '0' COMMENT '分值',
  `title` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '测评结果标题',
  `content` LONGTEXT COMMENT '测评正文',
  `createtime` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_content` (`content`(10))
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='医生测评报告';

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
     `score` INT(11) NOT NULL DEFAULT '0' COMMENT '测评分数',
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



DROP TABLE IF EXISTS `hd_user_cash_log`;
CREATE TABLE `hd_user_cash_log` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `userid` INT(11) NOT NULL DEFAULT '0' COMMENT '用户id',
   `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 提现 1到账',
  `money` INT(11) NOT NULL DEFAULT '0' COMMENT '提现金额',
  `bank` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '银行',
  `card` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '卡号',
  `createtime` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '是否审核通过',
  PRIMARY KEY (`id`),
  KEY `idx_userid` (`userid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户提现记录';



DROP TABLE IF EXISTS `hd_user_article_comment`;
CREATE TABLE `hd_user_article_comment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `userid` INT(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `articleid` INT(11) NOT NULL DEFAULT '0' COMMENT '文章id',
  `isreply` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是回复',
  `pid` INT(11) NOT NULL DEFAULT '0' COMMENT '回复评论id',
  `comment` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '评论',
  `createtime` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '是否审核通过',
  PRIMARY KEY (`id`),
  KEY `idx_userid` (`userid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户文章评论';

DROP TABLE IF EXISTS `hd_user_article_comment_like`;
CREATE TABLE `hd_user_article_comment_like` (
  `userid` INT(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `articleid` INT(11) NOT NULL DEFAULT '0' COMMENT '文章id',
  `commentid`  INT(11) NOT NULL DEFAULT '0' COMMENT '评论id',
  PRIMARY KEY (`userid`,`articleid`,`commentid`),
  KEY `idx_userid` (`userid`),
  KEY `idx_articleid` (`articleid`),
  KEY `idx_commentid` (`commentid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户文章评论点赞';

















