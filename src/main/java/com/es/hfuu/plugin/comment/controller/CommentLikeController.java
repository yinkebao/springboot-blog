package com.es.hfuu.plugin.comment.controller;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeConsumer;
import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeFunction;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.es.hfuu.common.vo.EsResult;
import com.es.hfuu.plugin.comment.domain.CommentLike;
import com.es.hfuu.plugin.comment.service.CommentLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName CommentLikeController
 * @Description
 * @Author ykb
 * @Date 2020/4/20
 */
@Api(tags = "文章评论点赞")
@Controller
@RequestMapping("/system/commentLike")
public class CommentLikeController {

  @Autowired
  private CommentLikeService commentLikeService;

  @ApiOperation(value = "点赞")
  @RequestMapping(value = "/like", method = RequestMethod.POST)
  @ResponseBody
  public EsResult<Boolean> like(CommentLike commentLike) {
    return providerServiceInvokeConsumer(commentLikeService::save,commentLike);
  }

  @ApiOperation(value = "获取评论点赞数")
  @RequestMapping(value = "/countLikeNum", method = RequestMethod.POST)
  @ResponseBody
  public EsResult<Integer> countLikeNum(Long commentId) {
    return providerServiceInvokeFunction(commentLikeService::count,new QueryWrapper<CommentLike>()
        .lambda()
        .eq(CommentLike::getCommentId,commentId)
    );
  }

  @ApiOperation(value = "用户是否对该评论点赞")
  @RequestMapping(value = "/ifLike", method = RequestMethod.POST)
  @ResponseBody
  public EsResult<Boolean> ifLike(CommentLike commentLike) {
    return providerServiceInvokeFunction(commentLikeService::ifLike,commentLike.getCommentId(),commentLike.getUserId());
  }
}
