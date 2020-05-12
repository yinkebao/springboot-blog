package com.es.hfuu.plugin.blog.controller;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeConsumer;
import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeFunction;

import com.es.hfuu.common.controller.BaseController;
import com.es.hfuu.common.vo.EsResult;
import com.es.hfuu.plugin.blog.domain.ArticleCollect;
import com.es.hfuu.plugin.blog.domain.ArticleView;
import com.es.hfuu.plugin.blog.service.ArticleViewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ArticleViewController
 * @Description
 * @Author ykb
 * @Date 2020/4/20
 */
@Api(tags = "文章阅读")
@Controller
@RequestMapping("/system/articleView")
public class ArticleViewController extends BaseController {

  @Autowired
  private ArticleViewService articleViewService;

  @ApiOperation(value = "是否阅读", notes = "判断用户是否收藏该文章")
  @RequestMapping(value = "/isViewed", method = RequestMethod.GET)
  @ResponseBody
  public EsResult<Boolean> isViewed(Long articleId, Long userId) {
    return providerServiceInvokeFunction(articleViewService::isViewed, articleId, userId);
  }

  @ApiOperation(value = "阅读")
  @RequestMapping(value = "/view", method = RequestMethod.POST)
  @ResponseBody
  public EsResult<Boolean> view(ArticleView articleView) {
    return providerServiceInvokeConsumer(articleViewService::view,articleView);
  }
}
