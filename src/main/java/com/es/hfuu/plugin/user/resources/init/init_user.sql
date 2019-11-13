-- 新建用户表
CREATE TABLE `users` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id' ,
`nickName`  varchar(255) NULL COMMENT '昵称' ,
`userName`  varchar(255) NOT NULL COMMENT '用户名' ,
`password`  varchar(255) NOT NULL COMMENT '密码' ,
`phone`  varchar(255) NOT NULL COMMENT '联系方式' ,
`birthDay`  datetime NULL COMMENT '生日' ,
`email`  varchar(255) NOT NULL COMMENT '邮箱' ,
`headerUrl`  varchar(255) NULL COMMENT '图像路径' ,
`lastLoginTime`  datetime NULL COMMENT '最后登录时间' ,
`lastLoginIp`  varchar(255) NULL COMMENT '最后登录IP' ,
`previousLoginTime`  datetime NULL COMMENT '上次登录时间' ,
`previousLoginIp`  varchar(255) NULL COMMENT '上次登录Ip' ,
`failureTimes`  int(8) NULL COMMENT '登录失败的次数' ,
`admin`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为系统管理员' ,
`shutDown`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '用户是否启用' ,
`lock`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已锁定' ,
`createUser`  varchar(255) NOT NULL COMMENT '数据添加人' ,
`createDate`  datetime NOT NULL COMMENT '数据添加时间' ,
`updateUser`  varchar(255) NULL COMMENT '数据修改人' ,
`updateDate`  datetime NULL COMMENT '数据修改日期' ,
`deleted`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '数据的状态(0:保存，1：删除)' ,
PRIMARY KEY (`id`)
);