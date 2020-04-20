package com.es.hfuu.plugin.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.es.hfuu.plugin.comment.domain.CommentLike;
import com.es.hfuu.plugin.comment.mapper.CommentLikeMapper;
import com.es.hfuu.plugin.comment.service.CommentLikeService;
import org.springframework.stereotype.Service;

/**
 * @ClassName CommentLikeServiceImpl
 * @Description
 * @Author ykb
 * @Date 2020/4/20
 */
@Service("commentLikeService")
public class CommentLikeServiceImpl extends ServiceImpl<CommentLikeMapper, CommentLike> implements CommentLikeService {

  /**
   * 是否点赞
   *
   * @param commentId 评论id
   * @param userId 用户id
   * @return Boolean
   */
  @Override
  public Boolean ifLike(Long commentId, Long userId) {
    return 0 != this.count(new QueryWrapper<CommentLike>()
        .lambda()
        .eq(CommentLike::getCommentId,commentId)
        .eq(CommentLike::getUserId,userId)
    );
  }
}
