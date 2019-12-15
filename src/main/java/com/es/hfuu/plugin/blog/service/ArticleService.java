package com.es.hfuu.plugin.blog.service;

import com.es.hfuu.plugin.blog.domain.Article;
import com.es.hfuu.plugin.blog.vo.ArticleVO;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName ArticleService
 * @Description 文章服务层接口
 * @Author ykb
 * @Date 2019/12/11
 */
public interface ArticleService{

    /**
     * 获取分页列表
     *
     * @param articleVO 查询参数
     * @return Article
     */
    PageInfo<Article> listArticlesByParamForPage(ArticleVO articleVO);

    /**
     * 保存文章信息
     *
     * @param article 文章对象
     * @return Article
     */
    Article saveArticle(Article article);

    /**
     * 修改文章信息
     *
     * @param article 文章对象
     * @return Article
     */
    Article updateArticle(Article article);

    /**
     * 根据文章Id获取文章的简单信息
     *
     * @param id 文章Id
     * @return Article 文章对象
     */
    Article getSimpleArticleById(Long id);

    /**
     * 根据文章Id获取文章的全部信息
     *
     * @param id 文章Id
     * @return Article 文章对象
     */
    Article getFullArticleById(Long id);

    /**
     * 根据文章Ids删除文章
     *
     * @param ids 文章Ids
     * @return int
     */
    int deleteArticlesByIds(String ids);
}
