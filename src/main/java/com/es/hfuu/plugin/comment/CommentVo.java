package com.es.hfuu.plugin.comment;

import com.es.hfuu.plugin.user.domain.User;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName CommentVo
 * @Description
 * @Author ykb
 * @Date 2020/4/15
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("评论")
public class CommentVo implements Serializable {

  /**
   * id
   */
  private Long id;

  /**
   * 文章id
   */
  private Long articleId;

  /**
   * 父id（被评论的评论）
   */
  private Long parentId;

  /**
   * 评论内容
   */
  private String content;

  /**
   * 用户id
   */
  private Long userId;

  /**
   * 用户id
   */
  private User user;

  /**
   * 创建时间
   */
  private Date createDate;

  /**
   * 创建时间
   */
  private String createDateStr;

  /**
   * 点赞数
   */
  private Integer likeNum;

  /**
   * 父评论用户名
   */
  private String commentedNickName;

  /**
   * 评论下评论
   */
  private List<CommentVo> childes;

  @Builder
  public CommentVo(Long id, Long articleId, Long parentId, String content, Long userId,
      User user, Date createDate, String createDateStr, Integer likeNum, String commentedNickName,
      List<CommentVo> childes) {
    this.id = id;
    this.articleId = articleId;
    this.parentId = parentId;
    this.content = content;
    this.userId = userId;
    this.user = user;
    this.createDate = createDate;
    this.createDateStr = createDateStr;
    this.likeNum = likeNum;
    this.commentedNickName = commentedNickName;
    this.childes = childes;
  }
}
