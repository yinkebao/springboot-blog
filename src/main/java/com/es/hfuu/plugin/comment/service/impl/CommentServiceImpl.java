package com.es.hfuu.plugin.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.es.hfuu.plugin.blog.domain.UserArticleScore;
import com.es.hfuu.plugin.blog.service.UserArticleScoreService;
import com.es.hfuu.plugin.comment.CommentVo;
import com.es.hfuu.plugin.comment.domain.Comment;
import com.es.hfuu.plugin.comment.domain.CommentLike;
import com.es.hfuu.plugin.comment.mapper.CommentMapper;
import com.es.hfuu.plugin.comment.service.CommentLikeService;
import com.es.hfuu.plugin.comment.service.CommentService;
import com.es.hfuu.plugin.user.service.UserService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName CommentServiceImpl
 * @Description
 * @Author ykb
 * @Date 2020/4/15
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements
    CommentService {

  @Autowired
  private CommentMapper commentMapper;

  @Autowired
  private UserService userService;

  @Autowired
  private CommentLikeService commentLikeService;

  @Autowired
  private UserArticleScoreService userArticleScoreService;

  /**
   * 评论
   *
   * @param comment 评论内容
   * @return Comment
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public Comment saveComment(Comment comment) {
    commentMapper.insert(comment);
    if (userArticleScoreService.ifExist(UserArticleScore.builder()
        .articleId(comment.getArticleId())
        .userId(comment.getUserId())
        .build())) {
      userArticleScoreService
          .addScore(userArticleScoreService.getOne(new QueryWrapper<UserArticleScore>()
              .lambda()
              .eq(UserArticleScore::getUserId, comment.getUserId())
              .eq(UserArticleScore::getArticleId, comment.getArticleId())
          ), 2);
    }
    return this.getById(comment.getId());
  }

  /**
   * 删除评论
   *
   * @param commentId 评论id
   */
  @Override
  public void deleteComment(Long commentId) {
    this.removeById(commentId);
  }

  /**
   * 删除文章所有评论
   *
   * @param articleId 文章id
   * @return Comment
   */
  @Override
  public void deleteComments(Long articleId) {
    this.remove(new QueryWrapper<Comment>().lambda().eq(Comment::getArticleId, articleId));
  }

  /**
   * 统计文章评论数
   *
   * @param articleId 文章id
   */
  @Override
  public Integer countCommentNum(Long articleId) {
    return this.count(new QueryWrapper<Comment>().lambda().eq(Comment::getArticleId,articleId));
  }

  /**
   * 获取文章评论列表
   *
   * @param articleId 文章id
   * @return Comment
   */
  @Override
  public List<CommentVo> findComments(Long articleId) {
    List<Comment> comments = this.list(new QueryWrapper<Comment>().lambda()
            .eq(Comment::getArticleId, articleId)
            .isNull(Comment::getParentId)
        );
    List<CommentVo> commentVos = new ArrayList<>();
    comments.forEach(comment -> {
      commentVos.add(CommentVo.builder()
          .id(comment.getId())
          .articleId(comment.getArticleId())
          .content(comment.getContent())
          .likeNum(commentLikeService.count(new QueryWrapper<CommentLike>()
              .lambda()
              .eq(CommentLike::getCommentId,comment.getId())))
          .createDateStr(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(comment.getCreateDate()))
          .user(userService.getSimpleUserById(comment.getUserId()))
          .childes(this.findChildComments(comment.getId()))
          .build());
    });
    return commentVos;
  }

  /**
   * 获取评论下评论
   *
   * @param commentId 评论id
   * @return Comment
   */
  @Override
  public List<CommentVo> findChildComments(Long commentId) {
    List<Comment> comments = this.list(new QueryWrapper<Comment>().lambda()
        .eq(Comment::getParentId, commentId)
    );
    List<CommentVo> commentVos = new ArrayList<>();
    comments.forEach(comment -> {
      commentVos.add(CommentVo.builder()
          .id(comment.getId())
          .articleId(comment.getArticleId())
          .content(comment.getContent())
          .parentId(comment.getParentId())
          .createDateStr(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(comment.getCreateDate()))
          .commentedNickName(userService.getSimpleUserById(comment.getCommentedUserId()).getNickName())
          .user(userService.getSimpleUserById(comment.getUserId()))
          .build());
    });
    return commentVos;
  }
}
