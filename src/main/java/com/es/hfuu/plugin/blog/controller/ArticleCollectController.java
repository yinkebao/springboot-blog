package com.es.hfuu.plugin.blog.controller;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeConsumer;
import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeFunction;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.es.hfuu.common.controller.BaseController;
import com.es.hfuu.common.vo.EsResult;
import com.es.hfuu.plugin.blog.domain.ArticleCollect;
import com.es.hfuu.plugin.blog.domain.ArticleTag;
import com.es.hfuu.plugin.blog.service.ArticleCollectService;
import com.es.hfuu.plugin.comment.domain.CommentLike;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ArticleCollectController
 * @Description
 * @Author ykb
 * @Date 2020/4/20
 */
@Api(tags = "文章收藏")
@Controller
@RequestMapping("/system/articleCollection")
public class ArticleCollectController extends BaseController {

  @Autowired
  private ArticleCollectService articleCollectService;

  @ApiOperation(value = "是否收藏", notes = "判断用户是否收藏该文章")
  @RequestMapping(value = "/getArticleTagById", method = RequestMethod.GET)
  @ResponseBody
  public EsResult<Boolean> getArticleTagById(Long articleId, Long userId) {
    return providerServiceInvokeFunction(articleCollectService::isCollected, articleId, userId);
  }

  @ApiOperation(value = "收藏")
  @RequestMapping(value = "/collect", method = RequestMethod.POST)
  @ResponseBody
  public EsResult<Boolean> collect(ArticleCollect articleCollect) {
    return providerServiceInvokeConsumer(articleCollectService::save,articleCollect);
  }

  @ApiOperation(value = "取消收藏")
  @RequestMapping(value = "/cancelCollect", method = RequestMethod.POST)
  @ResponseBody
  public EsResult<Boolean> cancelCollect(ArticleCollect articleCollect) {
    return providerServiceInvokeConsumer(articleCollectService::remove,new QueryWrapper<ArticleCollect>()
        .lambda()
        .eq(ArticleCollect::getArticleId,articleCollect.getArticleId())
        .eq(ArticleCollect::getUserId,articleCollect.getUserId())
    );
  }
}
