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
 * @ClassName ArticleCollect
 * @Description 文章收藏
 * @Author ykb
 * @Date 2020/4/20
 */
@Setter
@Getter
@NoArgsConstructor
@ApiModel("文章收藏")
@TableName("article_collect")
public class ArticleCollect extends BaseDomain {

  /**
   * 文章id
   */
  @TableField("article_id")
  private Long articleId;

  /**
   * 用户id
   */
  @TableField("user_id")
  private Long userId;

  @Builder
  public ArticleCollect(Long id, String createUser, Date createDate,
      String updateUser, Date updateDate, Boolean isDeleted, Long articleId,
      Long userId) {
    super(id, createUser, createDate, updateUser, updateDate, isDeleted);
    this.articleId = articleId;
    this.userId = userId;
  }
}
