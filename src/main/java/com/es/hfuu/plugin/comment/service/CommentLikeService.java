package com.es.hfuu.plugin.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.es.hfuu.plugin.comment.domain.CommentLike;

/**
 * @ClassName CommentLikeService
 * @Description
 * @Author ykb
 * @Date 2020/4/20
 */
public interface CommentLikeService extends IService<CommentLike> {

  /**
   * 是否点赞
   *
   * @param commentId 评论id
   * @param userId 用户id
   * @return Boolean
   */
  Boolean ifLike(Long commentId, Long userId);
}
