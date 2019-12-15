-- 新建用户表
CREATE TABLE `sys_user` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id' ,
  `nick_name`  varchar(255) NULL COMMENT '昵称' ,
  `user_name`  varchar(255) NOT NULL COMMENT '用户名' ,
  `password`  varchar(255) NOT NULL COMMENT '密码' ,
  `phone`  varchar(255) NOT NULL COMMENT '联系方式' ,
  `birth_day`  datetime NULL COMMENT '生日' ,
  `email`  varchar(255) NOT NULL COMMENT '邮箱' ,
  `header_url`  varchar(255) NULL COMMENT '图像路径' ,
  `last_login_time`  datetime NULL COMMENT '最后登录时间' ,
  `last_login_ip`  varchar(255) NULL COMMENT '最后登录IP' ,
  `previous_login_time`  datetime NULL COMMENT '上次登录时间' ,
  `previous_login_ip`  varchar(255) NULL COMMENT '上次登录Ip' ,
  `failure_times`  int(8) NULL COMMENT '登录失败的次数' ,
  `admin`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为系统管理员' ,
  `is_enabled`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '用户是否启用' ,
  `is_lock`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已锁定' ,
  `create_user`  varchar(255) NOT NULL COMMENT '数据添加人' ,
  `create_date`  datetime NOT NULL COMMENT '数据添加时间' ,
  `update_user`  varchar(255) NULL COMMENT '数据修改人' ,
  `update_date`  datetime NULL COMMENT '数据修改日期' ,
  `is_deleted`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '数据的状态(0:保存，1：删除)' ,
  PRIMARY KEY (`id`)
);
-- 初始一个管理员用户
INSERT INTO `sys_user` (`id`, `nick_name`, `user_name`, `password`, `phone`, `birth_day`, `email`, `header_url`, `last_login_time`, `last_login_ip`, `previous_login_time`, `previous_login_ip`, `failure_times`, `admin`, `is_enabled`, `is_lock`, `create_user`, `create_date`, `update_user`, `update_date`, `is_deleted`)
VALUES ('134958588869487', '胖胖罗', 'lsx', '1bbd886460827015e5d605ed44252251', '15556532746', '2001-10-11 00:00:00', 'yinkebao@hztianque.com', NULL, '2019-12-12 13:54:21', '192.168.40.183', NULL, NULL, '0', '0', '0', '0', 'admin', '2019-12-12 13:55:00', NULL, NULL, '0');

-- 创建session表
CREATE TABLE `sys_user_session` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id' ,
  `session_id`  varchar(255) NOT NULL COMMENT '会话id' ,
  `user_id`  bigint(20) NOT NULL COMMENT '用户id' ,
  `user_name`  varchar(255) NOT NULL COMMENT '用户名' ,
  `nick_name`  varchar(255) COMMENT '昵称' ,
  `last_url`  varchar(255) NULL COMMENT '上一次访问链接' ,
  `access_time`  datetime NULL COMMENT '上一次访问时间' ,
  `access_ip`  varchar(255) NULL COMMENT '上一次访问Ip地址' ,
  `is_login`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否登录' ,
  `login_date`  datetime NULL COMMENT '登录时间' ,
  `login_ip`  varchar(255) NULL COMMENT '登录IP地址' ,
  `validate_code`  varchar(255) NULL COMMENT '登录验证码' ,
  PRIMARY KEY (`id`)
);