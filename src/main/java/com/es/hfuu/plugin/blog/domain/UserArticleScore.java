package com.es.hfuu.plugin.blog.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.es.hfuu.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName UserArticleScore
 * @Description
 * @Author ykb
 * @Date 2020/5/10
 */
@Setter
@Getter
@NoArgsConstructor
@ApiModel("用户文章评分")
@TableName("user_article_score")
public class UserArticleScore extends BaseDomain {

  /** 用户id*/
  @TableField("user_id")
  private Long userId;

  /** 文章id*/
  @TableField("article_id")
  private Long articleId;

  /** 分数（用户对文章的好感度）*/
  @TableField("score")
  private Integer score;

  @Builder
  public UserArticleScore(Long id, String createUser, Date createDate,
	  String updateUser, Date updateDate, Boolean isDeleted, Long userId,
	  Long articleId, Integer score) {
	super(id, createUser, createDate, updateUser, updateDate, isDeleted);
	this.userId = userId;
	this.articleId = articleId;
	this.score = score;
  }
}
