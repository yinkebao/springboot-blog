package com.es.hfuu.plugin.blog.controller;

import com.es.hfuu.common.controller.BaseController;
import com.es.hfuu.common.vo.EsResult;
import com.es.hfuu.plugin.blog.domain.Article;
import com.es.hfuu.plugin.blog.service.ArticleService;
import com.es.hfuu.plugin.blog.service.ArticleTypeService;
import com.es.hfuu.plugin.blog.vo.ArticleVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeConsumer;
import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeFunction;
import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeSupplier;

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

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTypeService articleTypeService;

    @ApiOperation(value = "文章管理页面")
    @GetMapping("/list")
    public String listPage(Model model) {
        model.addAttribute("url_mode","articleList");
        return "/blog/articleList";
    }

    @ApiOperation(value = "分页查询文章列表信息")
    @RequestMapping(value="/page",method = RequestMethod.GET)
    @ResponseBody
    public EsResult<PageInfo<Article>> listArticlesByParamForPage(ArticleVO articleVO) {
        return providerServiceInvokeFunction(articleService::listArticlesByParamForPage,articleVO);
    }

    @ApiOperation(value = "分页查询已发布文章列表信息")
    @RequestMapping(value="/listArticles",method = RequestMethod.GET)
    @ResponseBody
    public EsResult<PageInfo<Article>> listArticlesByParam(ArticleVO articleVO) {
        return providerServiceInvokeFunction(articleService::listArticlesByParam,articleVO);
    }

    @ApiOperation(value = "所有文章top5")
    @RequestMapping(value="/listTopFiveArticles",method = RequestMethod.GET)
    @ResponseBody
    public <R>EsResult<List<Article>> listTopFiveArticles() {
        return providerServiceInvokeSupplier(articleService::listTopFiveArticles);
    }

    @ApiOperation(value = "本周top3")
    @RequestMapping(value="/listWeeFireArticles",method = RequestMethod.GET)
    @ResponseBody
    public <R>EsResult<List<Article>> listWeeFireArticles() {
        return providerServiceInvokeSupplier(articleService::listWeeFireArticles);
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

    @ApiOperation(value = "发布文章", notes = "发布文章")
    @RequestMapping(value="/publishArticle",method = RequestMethod.PATCH)
    @ResponseBody
    public EsResult<Article> publishArticle(Long id) {
        return providerServiceInvokeFunction(articleService::publishArticle,id);
    }

    @ApiOperation(value = "下架文章", notes = "下架文章")
    @RequestMapping(value="/takeOffPublishArticle",method = RequestMethod.PATCH)
    @ResponseBody
    public EsResult<Article> takeOffPublishArticle(Long id) {
        return providerServiceInvokeFunction(articleService::takeOffPublishArticle,id);
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

  @ApiOperation(value = "查看文章")
  @GetMapping("/viewArticle")
  public String viewArticle(Long id, Model model) {
    model.addAttribute("article",articleService.getFullArticleById(id));
    return "/viewArticle";
  }

    @ApiOperation(value = "新增UI")
    @GetMapping("/addUI")
    public String addUI(String mode, Model model) {
        model.addAttribute("mode",mode);
        model.addAttribute("articleTypes",articleTypeService.listArticleTypes());
        return "/blog/articleForm";
    }

    @ApiOperation(value = "修改UI")
    @GetMapping("/edit/{id}")
    public String editUI(@PathVariable("id") Long id, String mode, Model model) {
        model.addAttribute("article",articleService.getSimpleArticleById(id));
        model.addAttribute("articleTypes",articleTypeService.listArticleTypes());
        model.addAttribute("mode",mode);
        return "/blog/articleForm";
    }


}
