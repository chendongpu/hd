insert into hd_user (username, password, createtime,content) values ('apple', 'e10adc3949ba59abbe56e057f20f883e', now(),'');
insert into hd_user_address (userid, realname,mobile,province,city,area,address, createtime) values (1,'陈东谱', 18329123270,'湖北省','武穴市','余川镇','龟山村3组47号', now());
insert into hd_doctor (realname,username, password, createtime,content) values ('王医生','wang', 'e10adc3949ba59abbe56e057f20f883e', now(),'');
insert into hd_video (doctorid,title, video_img, video_url,createtime) values (1,'王医生讲座', 'http://','http://',now());
insert into hd_article (article_title, article_img, article_content,createtime,article_readnum,article_likenum) values ('武汉,加油', 'http://','武汉一定要挺住，向白衣天使致敬，你们辛苦了，熬过这个艰难的时刻，武汉加油！',now(),1,1);
insert into hd_article_log (aid, userid, `read`,createtime) values (1,1,1,now());
insert into hd_article_log (aid, userid, `like`,createtime) values (1,1,1,now());
