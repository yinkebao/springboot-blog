package com.es.hfuu.plugin.comment.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.es.hfuu.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName Comment
 * @Description 评论实体
 * @Author ykb
 * @Date 2020/4/15
 */
@Setter
@Getter
@NoArgsConstructor
@ApiModel("文章评论")
@TableName("comment")
public class Comment extends BaseDomain {

  /**
   * 文章id
   */
  @TableField("article_id")
  private Long articleId;

  /**
   * 父id（被评论的评论）
   */
  @TableField("parent_id")
  private Long parentId;

  /**
   * 评论内容
   */
  @TableField("content")
  private String content;

  /**
   * 用户id
   */
  @TableField("user_id")
  private Long userId;

  /**
   * 被评论人id
   */
  @TableField("commented_user_id")
  private Long commentedUserId;

  @Builder
  public Comment(Long id, String createUser, Date createDate, String updateUser,
      Date updateDate, Boolean isDeleted, Long articleId, Long parentId,
      String content, Long userId, Long commentedUserId) {
    super(id, createUser, createDate, updateUser, updateDate, isDeleted);
    this.articleId = articleId;
    this.parentId = parentId;
    this.content = content;
    this.userId = userId;
    this.commentedUserId = commentedUserId;
  }
}
