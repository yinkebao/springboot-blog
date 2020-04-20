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
 * @ClassName CommentLike
 * @Description
 * @Author ykb
 * @Date 2020/4/20
 */
@Setter
@Getter
@NoArgsConstructor
@ApiModel("文章点赞")
@TableName("comment_like")
public class CommentLike extends BaseDomain {

  /** 评论id */
  @TableField("comment_id")
  private Long commentId;

  /** 用户id */
  @TableField("user_id")
  private Long userId;

  @Builder
  public CommentLike(Long id, String createUser, Date createDate,
      String updateUser, Date updateDate, Boolean isDeleted, Long commentId,
      Long userId) {
    super(id, createUser, createDate, updateUser, updateDate, isDeleted);
    this.commentId = commentId;
    this.userId = userId;
  }
}
