package com.es.hfuu.plugin.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.es.hfuu.plugin.comment.CommentVo;
import com.es.hfuu.plugin.comment.domain.Comment;
import com.es.hfuu.plugin.comment.domain.CommentLike;
import java.util.List;

/**
 * @ClassName CommentService
 * @Description
 * @Author ykb
 * @Date 2020/4/15
 */
public interface CommentService extends IService<Comment> {

  /**
   * 评论
   *
   * @param comment 评论内容
   * @return Comment
   */
  Comment saveComment(Comment comment);

  /**
   * 删除评论
   *
   * @param commentId 评论id
   */
  void deleteComment(Long commentId);

  /**
   * 删除文章所有评论
   *
   * @param articleId 文章id
   */
  void deleteComments(Long articleId);

  /**
   * 统计文章评论数
   *
   * @param articleId 文章id
   */
  Integer countCommentNum(Long articleId);

  /**
   * 获取文章评论列表
   *
   * @param articleId 文章id
   * @return Comment
   */
  List<CommentVo> findComments(Long articleId);

  /**
   * 获取评论下评论
   *
   * @param commentId 评论id
   * @return Comment
   */
  List<CommentVo> findChildComments(Long commentId);
}
