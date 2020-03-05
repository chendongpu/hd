insert into hd_admin (username, password,createtime) values ('admin', 'e10adc3949ba59abbe56e057f20f883e',now());

insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('儿科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('妇产科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('内科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('中医科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('骨科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('消化内科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('男科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('外科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('耳鼻喉科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('心理科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('眼科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('营养科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('神经内科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('肿瘤内科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('口腔科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());
insert  into `hd_doctor_department`(`title`,`img`,`createtime`) values ('心内科','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg',now());


insert into hd_user (mobile,username, password,avatar, createtime,content) values ('15057190640','hd10001', 'e10adc3949ba59abbe56e057f20f883e','/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg', now(),'');
insert into hd_user (mobile,username, password, createtime,content) values ('15057190642','hd10002', 'e10adc3949ba59abbe56e057f20f883e', now(),'');
insert into hd_user (mobile,username, password, createtime,content) values ('15057190643','hd10003', 'e10adc3949ba59abbe56e057f20f883e', now(),'');
insert into hd_user (mobile,username, password, createtime,content) values ('15057190644','hd10004', 'e10adc3949ba59abbe56e057f20f883e', now(),'');
insert into hd_user (mobile,username, password, createtime,content) values ('15057190645','hd10005', 'e10adc3949ba59abbe56e057f20f883e', now(),'');
insert into hd_user (mobile,username, password, createtime,content) values ('15057190646','hd10006', 'e10adc3949ba59abbe56e057f20f883e', now(),'');
insert into hd_user (mobile,username, password, createtime,content) values ('15057190647','hd10007', 'e10adc3949ba59abbe56e057f20f883e', now(),'');
insert into hd_user (mobile,username, password, createtime,content) values ('15057190648','hd10008', 'e10adc3949ba59abbe56e057f20f883e', now(),'');
insert into hd_user (mobile,username, password, createtime,content) values ('15057190649','hd10009', 'e10adc3949ba59abbe56e057f20f883e', now(),'');
insert into hd_user (mobile,username, password, createtime,content) values ('15057190650','hd10010','e10adc3949ba59abbe56e057f20f883e', now(),'');
insert into hd_user (mobile,username, password, createtime,content) values ('15057190651','hd10011', 'e10adc3949ba59abbe56e057f20f883e', now(),'');


insert into hd_user (realname,mobile,username, password, createtime,content,isdoctor,department,hospital,`level`,goodat,`money`,`duration`) values ('王医生1','15057190652','hd10012', 'e10adc3949ba59abbe56e057f20f883e', now(),'',1,3,'人民医院','主任医师','擅长内科',2000,10);
insert into hd_user (realname,mobile,username, password, createtime,content,isdoctor,department,hospital,`level`,goodat,`money`,`duration`) values ('王医生2','15057190653','hd10013', 'e10adc3949ba59abbe56e057f20f883e', now(),'',1,8,'人民医院','主任医师','擅长外科',3000,15);
insert into hd_user (realname,mobile,username, password, createtime,content,isdoctor,department,hospital,`level`,goodat,`money`,`duration`) values ('王医生3','15057190654','hd10014', 'e10adc3949ba59abbe56e057f20f883e', now(),'',1,3,'人民医院','主任医师','擅长内科',1500,12);
insert into hd_user (realname,mobile,username, password, createtime,content,isdoctor,department,hospital,`level`,goodat,`money`,`duration`) values ('王医生4','15057190655','hd10015', 'e10adc3949ba59abbe56e057f20f883e', now(),'',1,1,'人民医院','主任医师','擅长儿科',2000,10);
insert into hd_user (realname,mobile,username, password, createtime,content,isdoctor,department,hospital,`level`,goodat,`money`,`duration`) values ('王医生5','15057190656','hd10016', 'e10adc3949ba59abbe56e057f20f883e', now(),'',1,3,'人民医院','主任医师','擅长内科',2000,10);
insert into hd_user (realname,mobile,username, password, createtime,content,isdoctor,department,hospital,`level`,goodat,`money`,`duration`) values ('王医生6','15057190657','hd10017','e10adc3949ba59abbe56e057f20f883e', now(),'',1,3,'人民医院','主任医师','擅长内科',1500,12);
insert into hd_user (realname,mobile,username, password, createtime,content,isdoctor,department,hospital,`level`,goodat,`money`,`duration`) values ('王医生7','15057190658','hd10018', 'e10adc3949ba59abbe56e057f20f883e', now(),'',1,3,'人民医院','主任医师','擅长内科',1800,15);
insert into hd_user (realname,mobile,username, password, createtime,content,isdoctor,department,hospital,`level`,goodat,`money`,`duration`) values ('王医生8','15057190659','hd10019', 'e10adc3949ba59abbe56e057f20f883e', now(),'',1,3,'人民医院','主任医师','擅长内科',2400,18);
insert into hd_user (realname,mobile,username, password, createtime,content,isdoctor,department,hospital,`level`,goodat,`money`,`duration`) values ('王医生9','15057190660','hd10020', 'e10adc3949ba59abbe56e057f20f883e', now(),'',1,3,'人民医院','主任医师','擅长内科',2500,15);
insert into hd_user (realname,mobile,username, password, createtime,content,isdoctor,department,hospital,`level`,goodat,`money`,`duration`) values ('王医生10','15057190661','hd10021', 'e10adc3949ba59abbe56e057f20f883e', now(),'',1,3,'人民医院','主任医师','擅长内科',1600,12);


insert  into `hd_treatment_order`(`orderid`,`userid`,`doctorid`,`money`,`duration`,`createtime`,`state`) values ('1000001556899032',1,12,2000,10,now(),0);
insert  into `hd_treatment_order`(`orderid`,`userid`,`doctorid`,`money`,`duration`,`createtime`,`state`) values ('1000001814621575',1,12,2000,10,now(),0);
insert  into `hd_treatment_order`(`orderid`,`userid`,`doctorid`,`money`,`duration`,`createtime`,`state`) values ('1000000419839389',1,12,2000,10,now(),0);
insert  into `hd_treatment_order`(`orderid`,`userid`,`doctorid`,`money`,`duration`,`createtime`,`state`) values ('1000001001799851',1,12,2000,10,now(),0);
insert  into `hd_treatment_order`(`orderid`,`userid`,`doctorid`,`money`,`duration`,`createtime`,`state`) values ('1000000146524839',1,12,2000,10,now(),0);
insert  into `hd_treatment_order`(`orderid`,`userid`,`doctorid`,`money`,`duration`,`createtime`,`state`) values ('1000001473789764',1,12,2000,10,now(),0);
insert  into `hd_treatment_order`(`orderid`,`userid`,`doctorid`,`money`,`duration`,`createtime`,`state`) values ('1000000556433802',1,12,2000,10,now(),0);
insert  into `hd_treatment_order`(`orderid`,`userid`,`doctorid`,`money`,`duration`,`createtime`,`state`) values ('1000000613496243',1,12,2000,10,now(),0);
insert  into `hd_treatment_order`(`orderid`,`userid`,`doctorid`,`money`,`duration`,`createtime`,`state`) values ('1000001491945635',1,12,2000,10,now(),0);
insert  into `hd_treatment_order`(`orderid`,`userid`,`doctorid`,`money`,`duration`,`createtime`,`state`) values ('1000000274037818',1,12,2000,10,now(),0);
insert  into `hd_treatment_order`(`orderid`,`userid`,`doctorid`,`money`,`duration`,`createtime`,`state`) values ('1000001772560398',1,12,2000,10,now(),0);

insert into hd_user_address (userid, realname,mobile,province,city,area,address, createtime) values (1,'陈东谱', 18329123270,'湖北省','武穴市','余川镇','龟山村3组47号', now());
insert into hd_user_address (userid, realname,mobile,province,city,area,address, createtime) values (1,'陈东谱', 15057190640,'湖北省','武穴市','余川镇','龟山村3组47号', now());
insert into hd_user_address (userid, realname,mobile,province,city,area,address, createtime) values (1,'陈东谱', 15057190641,'湖北省','武穴市','余川镇','龟山村3组47号', now());
insert into hd_user_address (userid, realname,mobile,province,city,area,address, createtime) values (1,'陈东谱', 15057190642,'湖北省','武穴市','余川镇','龟山村3组47号', now());
insert into hd_user_address (userid, realname,mobile,province,city,area,address, createtime) values (1,'陈东谱', 15057190643,'湖北省','武穴市','余川镇','龟山村3组47号', now());
insert into hd_user_address (userid, realname,mobile,province,city,area,address, createtime) values (1,'陈东谱', 15057190644,'湖北省','武穴市','余川镇','龟山村3组47号', now());
insert into hd_user_address (userid, realname,mobile,province,city,area,address, createtime) values (1,'陈东谱', 15057190645,'湖北省','武穴市','余川镇','龟山村3组47号', now());
insert into hd_user_address (userid, realname,mobile,province,city,area,address, createtime) values (1,'陈东谱', 15057190646,'湖北省','武穴市','余川镇','龟山村3组47号', now());
insert into hd_user_address (userid, realname,mobile,province,city,area,address, createtime) values (1,'陈东谱', 15057190647,'湖北省','武穴市','余川镇','龟山村3组47号', now());
insert into hd_user_address (userid, realname,mobile,province,city,area,address, createtime) values (1,'陈东谱', 15057190648,'湖北省','武穴市','余川镇','龟山村3组47号', now());
insert into hd_user_address (userid, realname,mobile,province,city,area,address, createtime) values (1,'陈东谱', 15057190649,'湖北省','武穴市','余川镇','龟山村3组47号', now());

insert into hd_article_category (title,createtime) values ('饮食健康',now());
insert into hd_article_category (title,createtime) values ('两性健康',now());
insert into hd_article_category (title,createtime) values ('孕产育儿',now());
insert into hd_article_category (title,createtime) values ('常见疾病',now());


insert into hd_user_article (userid,categoryid,title, img, content,createtime,readnum,likenum) values (1,1,'武汉,加油', 'http://','武汉一定要挺住，向白衣天使致敬，你们辛苦了，熬过这个艰难的时刻，武汉加油！',now(),1,1);
insert into hd_user_article (userid,categoryid,title, img, content,createtime,author,keyword,state) values (1,1,'武汉,加油01', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','武汉一定要挺住，向白衣天使致敬，你们辛苦了，熬过这个艰难的时刻，武汉加油！',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title, img, content,createtime,author,keyword,state) values (1,1,'武汉,加油02', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','武汉一定要挺住，向白衣天使致敬，你们辛苦了，熬过这个艰难的时刻，武汉加油！',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title, img, content,createtime,author,keyword,state) values (1,1,'武汉,加油03', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','武汉一定要挺住，向白衣天使致敬，你们辛苦了，熬过这个艰难的时刻，武汉加油！',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title, img, content,createtime,author,keyword,state) values (1,1,'武汉,加油04', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','武汉一定要挺住，向白衣天使致敬，你们辛苦了，熬过这个艰难的时刻，武汉加油！',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title, img, content,createtime,author,keyword,state) values (1,1,'武汉,加油05', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','武汉一定要挺住，向白衣天使致敬，你们辛苦了，熬过这个艰难的时刻，武汉加油！',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title, img, content,createtime,author,keyword,state) values (1,1,'武汉,加油06', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','武汉一定要挺住，向白衣天使致敬，你们辛苦了，熬过这个艰难的时刻，武汉加油！',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title, img, content,createtime,author,keyword,state) values (1,1,'武汉,加油07', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','武汉一定要挺住，向白衣天使致敬，你们辛苦了，熬过这个艰难的时刻，武汉加油！',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title, img, content,createtime,author,keyword,state) values (1,1,'武汉,加油08', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','武汉一定要挺住，向白衣天使致敬，你们辛苦了，熬过这个艰难的时刻，武汉加油！',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title, img, content,createtime,author,keyword,state) values (1,1,'武汉,加油09', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','武汉一定要挺住，向白衣天使致敬，你们辛苦了，熬过这个艰难的时刻，武汉加油！',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title,type, img, content,createtime,author,keyword,state) values (1,1,'野生动物',1, '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title,type, img, content,createtime,author,keyword,state) values (1,1,'野生动物01',1, '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title,type, img, content,createtime,author,keyword,state) values (1,1,'野生动物02',1, '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title,type, img, content,createtime,author,keyword,state) values (1,1,'野生动物03',1, '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title,type, img, content,createtime,author,keyword,state) values (1,1,'野生动物04',1, '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title,type, img, content,createtime,author,keyword,state) values (1,1,'野生动物05',1, '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title,type, img, content,createtime,author,keyword,state) values (1,1,'野生动物06',1, '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title,type, img, content,createtime,author,keyword,state) values (1,1,'野生动物07',1, '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title,type, img, content,createtime,author,keyword,state) values (1,1,'野生动物08',1, '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now(),'陈东谱','武汉',1);
insert into hd_user_article (userid,categoryid,title,type, img, content,createtime,author,keyword,state) values (1,1,'野生动物09',1, '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now(),'陈东谱','武汉',1);

insert into hd_user_article_read (userid,aid) values (1,1);
insert into hd_user_article_read (userid,aid) values (1,2);
insert into hd_user_article_read (userid,aid) values (1,3);
insert into hd_user_article_read (userid,aid) values (1,4);
insert into hd_user_article_read (userid,aid) values (1,5);
insert into hd_user_article_read (userid,aid) values (1,6);
insert into hd_user_article_read (userid,aid) values (1,7);
insert into hd_user_article_read (userid,aid) values (1,8);



insert into hd_user_concern (userid,concernid) values (1,2);
insert into hd_user_concern (userid,concernid) values (1,3);
insert into hd_user_concern (userid,concernid) values (1,4);
insert into hd_user_concern (userid,concernid) values (1,5);
insert into hd_user_concern (userid,concernid) values (1,6);
insert into hd_user_concern (userid,concernid) values (1,7);
insert into hd_user_concern (userid,concernid) values (2,1);
insert into hd_user_concern (userid,concernid) values (3,1);
insert into hd_user_concern (userid,concernid) values (4,1);
insert into hd_user_concern (userid,concernid) values (8,1);
insert into hd_user_concern (userid,concernid) values (9,1);
insert into hd_user_concern (userid,concernid) values (10,1);

insert into hd_user_video (userid,title, img, video,createtime) values (1,'apple分享视频', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now());
insert into hd_user_video (userid,title, img, video,createtime) values (1,'野生动物', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now());
insert into hd_user_video (userid,title, img, video,createtime) values (1,'野生动物01', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now());
insert into hd_user_video (userid,title, img, video,createtime) values (1,'野生动物02', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now());
insert into hd_user_video (userid,title, img, video,createtime) values (1,'野生动物03', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now());
insert into hd_user_video (userid,title, img, video,createtime) values (1,'野生动物04', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now());
insert into hd_user_video (userid,title, img, video,createtime) values (1,'野生动物05', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now());
insert into hd_user_video (userid,title, img, video,createtime) values (1,'野生动物06', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now());
insert into hd_user_video (userid,title, img, video,createtime) values (1,'野生动物07', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now());
insert into hd_user_video (userid,title, img, video,createtime) values (1,'野生动物08', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now());
insert into hd_user_video (userid,title, img, video,createtime) values (1,'野生动物09', '/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg','/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv',now());



insert into hd_user_article_collect (userid,aid) values (1,1);
insert into hd_user_article_collect (userid,aid) values (1,2);
insert into hd_user_article_collect (userid,aid) values (1,3);
insert into hd_user_article_collect (userid,aid) values (1,4);
insert into hd_user_article_collect (userid,aid) values (1,5);
insert into hd_user_article_collect (userid,aid) values (1,6);
insert into hd_user_article_collect (userid,aid) values (1,7);

insert into hd_user_test (userid,title, content,createtime) values (1,'测评标题01', '测评介绍01',now());
insert into hd_user_test (userid,title, content,createtime) values (1,'测评标题02', '测评介绍02',now());
insert into hd_user_test (userid,title, content,createtime) values (1,'测评标题03', '测评介绍03',now());
insert into hd_user_test (userid,title, content,createtime) values (1,'测评标题04', '测评介绍04',now());
insert into hd_user_test (userid,title, content,createtime) values (1,'测评标题05', '测评介绍05',now());
insert into hd_user_test (userid,title, content,createtime) values (1,'测评标题06', '测评介绍06',now());
insert into hd_user_test (userid,title, content,createtime) values (1,'测评标题07', '测评介绍07',now());
insert into hd_user_test (userid,title, content,createtime) values (1,'测评标题08', '测评介绍08',now());
insert into hd_user_test (userid,title, content,createtime) values (1,'测评标题09', '测评介绍09',now());


insert into hd_user_question (userid,title, type,score,createtime) values (1,'你选择网购的主要原因01?',0,10,now());
insert into hd_user_question (userid,title, type,score,createtime) values (1,'你选择网购的主要原因02?',1,10,now());
insert into hd_user_question (userid,title, type,score,createtime) values (1,'你选择网购的主要原因03?',0,10,now());
insert into hd_user_question (userid,title, type,score,createtime) values (1,'你选择网购的主要原因04?',0,10,now());
insert into hd_user_question (userid,title, type,score,createtime) values (1,'你选择网购的主要原因05?',1,10,now());
insert into hd_user_question (userid,title, type,score,createtime) values (1,'你选择网购的主要原因06?',1,10,now());
insert into hd_user_question (userid,title, type,score,createtime) values (1,'你选择网购的主要原因07?',0,10,now());
insert into hd_user_question (userid,title, type,score,createtime) values (1,'你选择网购的主要原因08?',0,10,now());
insert into hd_user_question (userid,title, type,score,createtime) values (1,'你选择网购的主要原因09?',0,10,now());


insert into hd_user_question_choice (questionid,choice) values (1,'方便快捷');
insert into hd_user_question_choice (questionid,choice) values (1,'品类齐全');
insert into hd_user_question_choice (questionid,choice) values (1,'价格便宜');

insert into hd_user_question_choice (questionid,choice) values (2,'方便快捷');
insert into hd_user_question_choice (questionid,choice) values (2,'品类齐全');
insert into hd_user_question_choice (questionid,choice) values (2,'价格便宜');

insert into hd_user_question_choice (questionid,choice) values (3,'方便快捷');
insert into hd_user_question_choice (questionid,choice) values (3,'品类齐全');
insert into hd_user_question_choice (questionid,choice) values (3,'价格便宜');

insert into hd_user_question_choice (questionid,choice) values (4,'方便快捷');
insert into hd_user_question_choice (questionid,choice) values (4,'品类齐全');
insert into hd_user_question_choice (questionid,choice) values (4,'价格便宜');

insert into hd_user_question_choice (questionid,choice) values (5,'方便快捷');
insert into hd_user_question_choice (questionid,choice) values (5,'品类齐全');
insert into hd_user_question_choice (questionid,choice) values (5,'价格便宜');

insert into hd_user_question_choice (questionid,choice) values (6,'方便快捷');
insert into hd_user_question_choice (questionid,choice) values (6,'品类齐全');
insert into hd_user_question_choice (questionid,choice) values (6,'价格便宜');

insert into hd_user_question_choice (questionid,choice) values (7,'方便快捷');
insert into hd_user_question_choice (questionid,choice) values (7,'品类齐全');
insert into hd_user_question_choice (questionid,choice) values (7,'价格便宜');

insert into hd_user_question_choice (questionid,choice) values (8,'方便快捷');
insert into hd_user_question_choice (questionid,choice) values (8,'品类齐全');
insert into hd_user_question_choice (questionid,choice) values (8,'价格便宜');

insert into hd_user_question_choice (questionid,choice) values (9,'方便快捷');
insert into hd_user_question_choice (questionid,choice) values (9,'品类齐全');
insert into hd_user_question_choice (questionid,choice) values (9,'价格便宜');

insert into hd_user_test_question (testid,questionid) values (1,1);
insert into hd_user_test_question (testid,questionid) values (1,2);
insert into hd_user_test_question (testid,questionid) values (1,3);

insert into hd_user_test_question (testid,questionid) values (2,1);
insert into hd_user_test_question (testid,questionid) values (2,2);
insert into hd_user_test_question (testid,questionid) values (2,3);

insert into hd_user_test_question (testid,questionid) values (3,1);
insert into hd_user_test_question (testid,questionid) values (3,2);
insert into hd_user_test_question (testid,questionid) values (3,3);

insert into hd_user_test_question (testid,questionid) values (4,1);
insert into hd_user_test_question (testid,questionid) values (4,2);
insert into hd_user_test_question (testid,questionid) values (4,3);


insert into hd_user_question_answer (questionid,choiceid) values (1,2);
insert into hd_user_question_answer (questionid,choiceid) values (2,4);
insert into hd_user_question_answer (questionid,choiceid) values (2,5);
insert into hd_user_question_answer (questionid,choiceid) values (3,9);

insert into hd_user_question_answer (questionid,choiceid) values (4,10);
insert into hd_user_question_answer (questionid,choiceid) values (5,14);
insert into hd_user_question_answer (questionid,choiceid) values (5,15);
insert into hd_user_question_answer (questionid,choiceid) values (6,16);
insert into hd_user_question_answer (questionid,choiceid) values (6,17);

insert into hd_user_question_answer (questionid,choiceid) values (7,19);
insert into hd_user_question_answer (questionid,choiceid) values (8,22);
insert into hd_user_question_answer (questionid,choiceid) values (9,27);

insert  into `hd_user_test_report`(`testid`,`score`,`title`,`content`,`createtime`) values (1,30,'很差','需要锻炼加强营养',now());
insert  into `hd_user_test_report`(`testid`,`score`,`title`,`content`,`createtime`) values (1,50,'一般','需要锻炼',now());
insert  into `hd_user_test_report`(`testid`,`score`,`title`,`content`,`createtime`) values (1,70,'很棒','需要继续保持',now());


insert  into `hd_user_test_log`(`userid`,`testid`,`title`,`content`,`createtime`,`score`,`result`) values (1,1,'测评标题01','测评介绍01',now(),10,'{\"packUserQuestionList\":[{\"result\":true,\"userQuestion\":{\"createtime\":1582104240000,\"id\":1,\"score\":10,\"title\":\"你选择网购的主要原因01?\",\"type\":0,\"userid\":1},\"userQuestionAnswerList\":[{\"choiceid\":2,\"questionid\":1}],\"userQuestionChoiceList\":[{\"choice\":\"方便快捷\",\"id\":1,\"questionid\":1},{\"choice\":\"品类齐全\",\"id\":2,\"questionid\":1},{\"choice\":\"价格便宜\",\"id\":3,\"questionid\":1}],\"yourAnswer\":[2]},{\"result\":false,\"userQuestion\":{\"createtime\":1582104240000,\"id\":3,\"score\":10,\"title\":\"你选择网购的主要原因03?\",\"type\":0,\"userid\":1},\"userQuestionAnswerList\":[{\"choiceid\":9,\"questionid\":3}],\"userQuestionChoiceList\":[{\"choice\":\"方便快捷\",\"id\":7,\"questionid\":3},{\"choice\":\"品类齐全\",\"id\":8,\"questionid\":3},{\"choice\":\"价格便宜\",\"id\":9,\"questionid\":3}],\"yourAnswer\":[6]},{\"result\":false,\"userQuestion\":{\"createtime\":1582104240000,\"id\":2,\"score\":10,\"title\":\"你选择网购的主要原因02?\",\"type\":1,\"userid\":1},\"userQuestionAnswerList\":[{\"choiceid\":4,\"questionid\":2},{\"choiceid\":5,\"questionid\":2}],\"userQuestionChoiceList\":[{\"choice\":\"方便快捷\",\"id\":4,\"questionid\":2},{\"choice\":\"品类齐全\",\"id\":5,\"questionid\":2},{\"choice\":\"价格便宜\",\"id\":6,\"questionid\":2}],\"yourAnswer\":[3,4]}],\"resultReport\":{\"content\":\"需要锻炼加强营养\",\"createtime\":1582104242000,\"id\":1,\"score\":30,\"testid\":1,\"title\":\"很差\"},\"userTestReportList\":[{\"content\":\"需要锻炼加强营养\",\"createtime\":1582104242000,\"id\":1,\"score\":30,\"testid\":1,\"title\":\"很差\"},{\"content\":\"需要锻炼\",\"createtime\":1582104242000,\"id\":2,\"score\":50,\"testid\":1,\"title\":\"一般\"},{\"content\":\"需要继续保持\",\"createtime\":1582104242000,\"id\":3,\"score\":70,\"testid\":1,\"title\":\"很棒\"}]}');
insert  into `hd_user_test_log`(`userid`,`testid`,`title`,`content`,`createtime`,`score`,`result`) values (1,1,'测评标题01','测评介绍01',now(),10,'{\"packUserQuestionList\":[{\"result\":true,\"userQuestion\":{\"createtime\":1582104240000,\"id\":1,\"score\":10,\"title\":\"你选择网购的主要原因01?\",\"type\":0,\"userid\":1},\"userQuestionAnswerList\":[{\"choiceid\":2,\"questionid\":1}],\"userQuestionChoiceList\":[{\"choice\":\"方便快捷\",\"id\":1,\"questionid\":1},{\"choice\":\"品类齐全\",\"id\":2,\"questionid\":1},{\"choice\":\"价格便宜\",\"id\":3,\"questionid\":1}],\"yourAnswer\":[2]},{\"result\":false,\"userQuestion\":{\"createtime\":1582104240000,\"id\":3,\"score\":10,\"title\":\"你选择网购的主要原因03?\",\"type\":0,\"userid\":1},\"userQuestionAnswerList\":[{\"choiceid\":9,\"questionid\":3}],\"userQuestionChoiceList\":[{\"choice\":\"方便快捷\",\"id\":7,\"questionid\":3},{\"choice\":\"品类齐全\",\"id\":8,\"questionid\":3},{\"choice\":\"价格便宜\",\"id\":9,\"questionid\":3}],\"yourAnswer\":[6]},{\"result\":false,\"userQuestion\":{\"createtime\":1582104240000,\"id\":2,\"score\":10,\"title\":\"你选择网购的主要原因02?\",\"type\":1,\"userid\":1},\"userQuestionAnswerList\":[{\"choiceid\":4,\"questionid\":2},{\"choiceid\":5,\"questionid\":2}],\"userQuestionChoiceList\":[{\"choice\":\"方便快捷\",\"id\":4,\"questionid\":2},{\"choice\":\"品类齐全\",\"id\":5,\"questionid\":2},{\"choice\":\"价格便宜\",\"id\":6,\"questionid\":2}],\"yourAnswer\":[3,4]}],\"resultReport\":{\"content\":\"需要锻炼加强营养\",\"createtime\":1582104242000,\"id\":1,\"score\":30,\"testid\":1,\"title\":\"很差\"},\"userTestReportList\":[{\"content\":\"需要锻炼加强营养\",\"createtime\":1582104242000,\"id\":1,\"score\":30,\"testid\":1,\"title\":\"很差\"},{\"content\":\"需要锻炼\",\"createtime\":1582104242000,\"id\":2,\"score\":50,\"testid\":1,\"title\":\"一般\"},{\"content\":\"需要继续保持\",\"createtime\":1582104242000,\"id\":3,\"score\":70,\"testid\":1,\"title\":\"很棒\"}]}');
insert  into `hd_user_test_log`(`userid`,`testid`,`title`,`content`,`createtime`,`score`,`result`) values (1,1,'测评标题01','测评介绍01',now(),10,'{\"packUserQuestionList\":[{\"result\":true,\"userQuestion\":{\"createtime\":1582104240000,\"id\":1,\"score\":10,\"title\":\"你选择网购的主要原因01?\",\"type\":0,\"userid\":1},\"userQuestionAnswerList\":[{\"choiceid\":2,\"questionid\":1}],\"userQuestionChoiceList\":[{\"choice\":\"方便快捷\",\"id\":1,\"questionid\":1},{\"choice\":\"品类齐全\",\"id\":2,\"questionid\":1},{\"choice\":\"价格便宜\",\"id\":3,\"questionid\":1}],\"yourAnswer\":[2]},{\"result\":false,\"userQuestion\":{\"createtime\":1582104240000,\"id\":3,\"score\":10,\"title\":\"你选择网购的主要原因03?\",\"type\":0,\"userid\":1},\"userQuestionAnswerList\":[{\"choiceid\":9,\"questionid\":3}],\"userQuestionChoiceList\":[{\"choice\":\"方便快捷\",\"id\":7,\"questionid\":3},{\"choice\":\"品类齐全\",\"id\":8,\"questionid\":3},{\"choice\":\"价格便宜\",\"id\":9,\"questionid\":3}],\"yourAnswer\":[6]},{\"result\":false,\"userQuestion\":{\"createtime\":1582104240000,\"id\":2,\"score\":10,\"title\":\"你选择网购的主要原因02?\",\"type\":1,\"userid\":1},\"userQuestionAnswerList\":[{\"choiceid\":4,\"questionid\":2},{\"choiceid\":5,\"questionid\":2}],\"userQuestionChoiceList\":[{\"choice\":\"方便快捷\",\"id\":4,\"questionid\":2},{\"choice\":\"品类齐全\",\"id\":5,\"questionid\":2},{\"choice\":\"价格便宜\",\"id\":6,\"questionid\":2}],\"yourAnswer\":[3,4]}],\"resultReport\":{\"content\":\"需要锻炼加强营养\",\"createtime\":1582104242000,\"id\":1,\"score\":30,\"testid\":1,\"title\":\"很差\"},\"userTestReportList\":[{\"content\":\"需要锻炼加强营养\",\"createtime\":1582104242000,\"id\":1,\"score\":30,\"testid\":1,\"title\":\"很差\"},{\"content\":\"需要锻炼\",\"createtime\":1582104242000,\"id\":2,\"score\":50,\"testid\":1,\"title\":\"一般\"},{\"content\":\"需要继续保持\",\"createtime\":1582104242000,\"id\":3,\"score\":70,\"testid\":1,\"title\":\"很棒\"}]}');


insert into hd_point_task (point,title, type,createtime) values (10,'马上签到，领取签到奖励10',0, now());
insert into hd_point_task (point,title, type,createtime) values (20,'马上签到，领取签到奖励20',0, now());
insert into hd_point_task (point,title, type,createtime) values (30,'马上签到，领取签到奖励30',0,now());
insert into hd_point_task (point,title, type,createtime) values (40,'马上签到，领取签到奖励40',0, now());


insert into hd_user_msg (userid,msg, type,otherid,createtime) values (1,'密码修改成功',0,0,now());
insert into hd_user_msg (userid,msg, type,otherid,createtime) values (1,'密码修改成功',0,0,now());
insert into hd_user_msg (userid,msg, type,otherid,createtime) values (1,'密码修改成功',0,0,now());
insert into hd_user_msg (userid,msg, type,otherid,createtime) values (1,'密码修改成功',0,0,now());
insert into hd_user_msg (userid,msg, type,otherid,createtime) values (1,'密码修改成功',0,0,now());
insert into hd_user_msg (userid,msg, type,otherid,createtime) values (1,'密码修改成功',0,0,now());
insert into hd_user_msg (userid,msg, type,otherid,createtime) values (1,'密码修改成功',0,0,now());
insert into hd_user_msg (userid,msg, type,otherid,createtime) values (1,'密码修改成功',0,0,now());
insert into hd_user_msg (userid,msg, type,otherid,createtime) values (1,'密码修改成功',0,0,now());
insert into hd_user_msg (userid,msg, type,otherid,createtime) values (1,'密码修改成功',0,0,now());


insert into `hd_user_task_log` (`userid`, `taskid`, `title`, `point`, `createtime`) values('1','1','马上签到，领取签到奖励10','10',now());
insert into `hd_user_task_log` (`userid`, `taskid`, `title`, `point`, `createtime`) values('1','1','马上签到，领取签到奖励10','10',now());
insert into `hd_user_task_log` (`userid`, `taskid`, `title`, `point`, `createtime`) values('1','1','马上签到，领取签到奖励10','10',now());
insert into `hd_user_task_log` (`userid`, `taskid`, `title`, `point`, `createtime`) values('1','1','马上签到，领取签到奖励10','10',now());
insert into `hd_user_task_log` (`userid`, `taskid`, `title`, `point`, `createtime`) values('1','1','马上签到，领取签到奖励10','10',now());
insert into `hd_user_task_log` (`userid`, `taskid`, `title`, `point`, `createtime`) values('1','1','马上签到，领取签到奖励10','10',now());
insert into `hd_user_task_log` (`userid`, `taskid`, `title`, `point`, `createtime`) values('1','1','马上签到，领取签到奖励10','10',now());
insert into `hd_user_task_log` (`userid`, `taskid`, `title`, `point`, `createtime`) values('1','1','马上签到，领取签到奖励10','10',now());
insert into `hd_user_task_log` (`userid`, `taskid`, `title`, `point`, `createtime`) values('1','1','马上签到，领取签到奖励10','10',now());
insert into `hd_user_task_log` (`userid`, `taskid`, `title`, `point`, `createtime`) values('1','1','马上签到，领取签到奖励10','10',now());

insert into `hd_user_cash_log` (`userid`, `money`, `bank`, `card`,  `createtime`) values('21',100092,'北京银行','62312321312313',now());
insert into `hd_user_cash_log` (`userid`, `money`, `bank`, `card`, `createtime`) values('21',100093,'北京银行','62312321312313',now());
insert into `hd_user_cash_log` (`userid`, `money`, `bank`, `card`, `createtime`) values('21',100094,'北京银行','62312321312313',now());
insert into `hd_user_cash_log` (`userid`, `money`, `bank`, `card`, `createtime`) values('21',100098,'北京银行','62312321312313',now());
insert into `hd_user_cash_log` (`userid`, `money`, `bank`, `card`, `createtime`) values('21',100099,'北京银行','62312321312313',now());
insert into `hd_user_cash_log` (`userid`, `money`, `bank`, `card`, `createtime`) values('21',100098,'北京银行','62312321312313',now());
insert into `hd_user_cash_log` (`userid`, `money`, `bank`, `card`, `createtime`) values('21',100097,'北京银行','62312321312313',now());
insert into `hd_user_cash_log` (`userid`, `money`, `bank`, `card`, `createtime`) values('21',100096,'北京银行','62312321312313',now());
insert into `hd_user_cash_log` (`userid`, `money`, `bank`, `card`, `createtime`) values('21',100095,'北京银行','62312321312313',now());
insert into `hd_user_cash_log` (`userid`, `money`, `bank`, `card`, `createtime`) values('21',100094,'北京银行','62312321312313',now());


insert  into `hd_user_article_comment`(`userid`,`articleid`,`comment`,`createtime`,`state`) values (1,1,'文章写的很棒！',now(),0);
insert  into `hd_user_article_comment`(`userid`,`articleid`,`comment`,`createtime`,`state`) values (1,1,'文章写的很棒！',now(),0);
insert  into `hd_user_article_comment`(`userid`,`articleid`,`comment`,`createtime`,`state`) values (1,1,'文章写的很棒！',now(),0);
insert  into `hd_user_article_comment`(`userid`,`articleid`,`comment`,`createtime`,`state`) values (1,1,'文章写的很棒！',now(),0);
insert  into `hd_user_article_comment`(`userid`,`articleid`,`comment`,`createtime`,`state`) values (1,1,'文章写的很棒！',now(),0);
insert  into `hd_user_article_comment`(`userid`,`articleid`,`comment`,`createtime`,`state`) values (1,1,'文章写的很棒！',now(),0);
insert  into `hd_user_article_comment`(`userid`,`articleid`,`comment`,`createtime`,`state`) values (1,1,'文章写的很棒！',now(),0);
insert  into `hd_user_article_comment`(`userid`,`articleid`,`comment`,`createtime`,`state`) values (1,1,'文章写的很棒！',now(),0);
insert  into `hd_user_article_comment`(`userid`,`articleid`,`comment`,`createtime`,`state`) values (1,1,'文章写的很棒！',now(),0);

insert into hd_user_article_comment_like (userid,articleid,commentid) values (1,1,1);
insert into hd_user_article_comment_like (userid,articleid,commentid) values (1,1,2);
insert into hd_user_article_comment_like (userid,articleid,commentid) values (1,1,3);
insert into hd_user_article_comment_like (userid,articleid,commentid) values (1,1,4);
insert into hd_user_article_comment_like (userid,articleid,commentid) values (1,1,5);
insert into hd_user_article_comment_like (userid,articleid,commentid) values (1,1,6);
insert into hd_user_article_comment_like (userid,articleid,commentid) values (1,1,7);