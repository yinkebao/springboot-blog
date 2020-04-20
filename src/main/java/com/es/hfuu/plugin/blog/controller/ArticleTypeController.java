package com.es.hfuu.plugin.blog.controller;

import com.es.hfuu.common.controller.BaseController;
import com.es.hfuu.common.vo.EsResult;
import com.es.hfuu.plugin.blog.domain.Article;
import com.es.hfuu.plugin.blog.domain.ArticleType;
import com.es.hfuu.plugin.blog.service.ArticleTypeService;
import com.es.hfuu.plugin.blog.vo.ArticleVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.*;

/**
 * @ClassName ArticleTypeController
 * @Description
 * @Author lsx
 * @Date 2019/12/17
 */
@Api(tags="文章类型管理",description = "包含新增、删除、修改、查询等操作")
@Controller
@RequestMapping("/system/articleType")
public class ArticleTypeController extends BaseController {

    @Autowired
    private ArticleTypeService articleTypeService;

    @ApiOperation(value = "查询文章类型列表信息")
    @RequestMapping(value="/listInfo",method = RequestMethod.GET)
    @ResponseBody
    public EsResult<List<ArticleType>> listArticleTypes() {
        return providerServiceInvokeSupplier(articleTypeService::listArticleTypes);
    }

}
