package com.es.hfuu.plugin.comment.controller;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeConsumer;
import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeFunction;

import com.es.hfuu.common.vo.EsResult;
import com.es.hfuu.plugin.comment.CommentVo;
import com.es.hfuu.plugin.comment.domain.Comment;
import com.es.hfuu.plugin.comment.domain.CommentLike;
import com.es.hfuu.plugin.comment.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName CommentController
 * @Description
 * @Author ykb
 * @Date 2020/4/15
 */
@Api(tags = "文章评论管理", description = "包含新增、删除、修改、查询等操作")
@Controller
@RequestMapping("/system/comment")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @ApiOperation(value = "添加评论")
  @RequestMapping(value = "/saveComment", method = RequestMethod.POST)
  @ResponseBody
  public EsResult<Comment> saveComment(Comment comment) {
    return providerServiceInvokeFunction(commentService::saveComment, comment);
  }

  @ApiOperation(value = "删除评论")
  @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
  @ResponseBody
  public EsResult<Boolean> deleteComment(Long commentId) {
    return providerServiceInvokeConsumer(commentService::deleteComment, commentId);
  }

  @ApiOperation(value = "删除文章所有评论")
  @RequestMapping(value = "/deleteComments", method = RequestMethod.POST)
  @ResponseBody
  public EsResult<Boolean> deleteComments(Long articleId) {
    return providerServiceInvokeConsumer(commentService::deleteComments, articleId);
  }

  @ApiOperation(value = "获取评论列表")
  @RequestMapping(value = "/listArticleComments", method = RequestMethod.POST)
  @ResponseBody
  public EsResult<List<CommentVo>> listArticleComments(Long articleId) {
    return providerServiceInvokeFunction(commentService::findComments, articleId);
  }

}
