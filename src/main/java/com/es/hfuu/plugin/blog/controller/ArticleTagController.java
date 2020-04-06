package com.es.hfuu.plugin.blog.controller;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeConsumer;
import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeFunction;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.es.hfuu.common.vo.EsResult;
import com.es.hfuu.plugin.blog.domain.ArticleTag;
import com.es.hfuu.plugin.blog.service.ArticleTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ArticleTag
 * @Description 文章标签控制层
 * @Author ykb
 * @Date 2020/3/22
 */
@Api(tags = "文章标签管理模块", description = "包含新增、删除、修改、查询等操作")
@Controller
@RequestMapping("/system/articleTag")
public class ArticleTagController {

  @Autowired
  private ArticleTagService articleTagService;

  @ApiOperation(value = "分页查询文章列表信息")
  @RequestMapping(value="/list",method = RequestMethod.GET)
  @ResponseBody
  public EsResult<List<ArticleTag>> listArticleTags() {
    return providerServiceInvokeFunction(articleTagService::list,new QueryWrapper());
  }

  @ApiOperation(value = "保存文章标签", notes = "根据ArticleTag对象创建文章")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "title", value = "标题", required = true)
  })
  @RequestMapping(value = "/saveArticleTag", method = RequestMethod.POST)
  @ResponseBody
  public EsResult<ArticleTag> saveArticleTag(ArticleTag articleTag) {
    return providerServiceInvokeFunction(articleTagService::saveArticleTag, articleTag);
  }

  @ApiOperation(value = "修改文章标签", notes = "根据ArticleTag对象修改文章")
  @RequestMapping(value = "/updateArticleTag", method = RequestMethod.PATCH)
  @ResponseBody
  public EsResult<ArticleTag> updateArticleTag(ArticleTag articleTag) {
    return providerServiceInvokeFunction(articleTagService::updateArticleTag, articleTag);
  }

  @ApiOperation(value = "根据id获取文章标签", notes = "根据articleTagId获取文章")
  @RequestMapping(value = "/getArticleTagById", method = RequestMethod.GET)
  @ResponseBody
  public EsResult<ArticleTag> getArticleTagById(Long id) {
    return providerServiceInvokeFunction(articleTagService::getById, id);
  }

  @ApiOperation(value = "根据标签名获取文章标签", notes = "根据articleTagId获取文章")
  @RequestMapping(value = "/getArticleTagByName", method = RequestMethod.GET)
  @ResponseBody
  public EsResult<ArticleTag> getArticleTagByName(String tagName) {
    return providerServiceInvokeFunction(articleTagService::getOne,
        new QueryWrapper<ArticleTag>().eq("tag_name", tagName));
  }

  @ApiOperation(value = "根据id删除文章标签", notes = "根据ids删除文章")
  @RequestMapping(value = "/deleteArticleTagsByIds", method = RequestMethod.DELETE)
  @ResponseBody
  public EsResult<Boolean> deleteArticleTagsByIds(@RequestParam String ids) {
    return providerServiceInvokeConsumer(articleTagService::deleteArticleTagsByIds, ids);
  }
}
