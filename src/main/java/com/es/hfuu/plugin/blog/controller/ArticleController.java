package com.es.hfuu.plugin.blog.controller;

import com.es.hfuu.common.controller.BaseController;
import com.es.hfuu.common.vo.EsResult;
import com.es.hfuu.plugin.blog.domain.Article;
import com.es.hfuu.plugin.blog.service.ArticleService;
import com.es.hfuu.plugin.blog.vo.ArticleVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeConsumer;
import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeFunction;

/**
 * @ClassName ArticleController
 * @Description 文章控制层
 * @Author ykb
 * @Date 2019/12/11
 */
@Api(tags="文章信息管理模块",description = "包含新增、删除、修改、查询等操作")
@Controller
@RequestMapping("/system/article")
public class ArticleController extends BaseController {

    @GetMapping("/list")
    public String listPage() {
        return "/blog/articleList";
    }

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "分页查询文章列表信息")
    @RequestMapping(value="/page",method = RequestMethod.GET)
    @ResponseBody
    public EsResult<PageInfo<Article>> listArticlesByParamForPage(ArticleVO articleVO) {
        return providerServiceInvokeFunction(articleService::listArticlesByParamForPage,articleVO);
    }

    @ApiOperation(value = "保存文章信息", notes = "根据Article对象创建文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "标题", required = true)
    })
    @RequestMapping(value="/saveArticle",method = RequestMethod.POST)
    @ResponseBody
    public EsResult<Article> saveArticle(Article article) {
        return providerServiceInvokeFunction(articleService::saveArticle,article);
    }

    @ApiOperation(value = "修改文章信息", notes = "根据Article对象修改文章")
    @RequestMapping(value="/updateArticle",method = RequestMethod.PATCH)
    @ResponseBody
    public EsResult<Article> updateArticle(Article article) {
        return providerServiceInvokeFunction(articleService::updateArticle,article);
    }

    @ApiOperation(value = "获取文章信息", notes = "根据articleId获取文章")
    @RequestMapping(value="/getArticleById",method = RequestMethod.GET)
    @ResponseBody
    public EsResult<Article> getArticleById(Long id) {
        return providerServiceInvokeFunction(articleService::getSimpleArticleById,id);
    }

    @ApiOperation(value = "根据id删除文章信息", notes = "根据ids删除文章")
    @RequestMapping(value="/deleteArticlesByIds",method = RequestMethod.DELETE)
    @ResponseBody
    public EsResult<Boolean> deleteArticlesByIds(@RequestParam String ids) {
        return providerServiceInvokeConsumer(articleService::deleteArticlesByIds,ids);
    }


}
