-- auto ykb on 2019-12-11
-- 文章表
CREATE TABLE article(
	id BIGINT (20) AUTO_INCREMENT COMMENT 'id',
	title VARCHAR (225) NOT NULL COMMENT '标题',
	author_id BIGINT (20) NOT NULL COMMENT '创建人id',
	original_author VARCHAR (50) NULL COMMENT '原作者',
	content BLOB NOT NULL COMMENT '内容',
  article_type_id BIGINT (20) NOT NULL COMMENT '博客类型id',
	source tinyint(1) NOT NULL DEFAULT 0 COMMENT '来源（0原创/1转载）',
	source_url VARCHAR (225)  COMMENT '原文链接',
	view_times INT (11) NOT NULL DEFAULT 0 COMMENT '浏览次数',
	collect_times INT (11) NOT NULL DEFAULT 0 COMMENT '收藏次数',
	is_publish int(1) NOT NULL default 0 COMMENT '是否发布',
	publish_date DATETIME  COMMENT '发布时间',
	is_idDelete tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0已删1未删',
	create_user VARCHAR (50) NOT NULL DEFAULT '' COMMENT '创建人',
	create_date DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
	update_user VARCHAR (50) NULL COMMENT '最后修改人',
	update_date DATETIME NULL COMMENT '最后修改时间',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '文章表';

-- 文章类型表
CREATE TABLE article_types(
	id BIGINT (20) AUTO_INCREMENT COMMENT 'id',
	e_name VARCHAR (225) NOT NULL COMMENT '英文名',
	c_name VARCHAR (225) NOT NULL COMMENT '中文名',
	is_idDelete tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0已删1未删',
	create_user VARCHAR (50) NOT NULL DEFAULT '' COMMENT '创建人',
	create_date DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
	update_user VARCHAR (50) NULL COMMENT '最后修改人',
	update_date DATETIME NULL COMMENT '最后修改时间',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '文章类型表';