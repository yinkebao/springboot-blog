package com.es.hfuu.plugin.blog.service.impl;

import com.es.hfuu.common.mapper.BaseMapper;
import com.es.hfuu.common.service.impl.BaseServiceImpl;
import com.es.hfuu.common.util.base.StringUtil;
import com.es.hfuu.plugin.blog.vo.ArticleVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.es.hfuu.plugin.blog.domain.Article;
import com.es.hfuu.plugin.blog.mapper.ArticleMapper;
import com.es.hfuu.plugin.blog.service.ArticleService;

/**
 * @ClassName ArticleServiceImpl
 * @Description 文章服务层实现
 * @Author ykb
 * @Date 2019/12/11
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article, ArticleVO>
        implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 获取分页列表
     *
     * @param articleVO 查询参数
     * @return Article
     */
    @Override
    public PageInfo<Article> listArticlesByParamForPage(ArticleVO articleVO) {
        articleVO.setRows(articleVO.getLimit());
        return listEntitiesForPageListByEntity(articleVO);
    }

    /**
     * 保存文章信息
     *
     * @param article 文章对象
     * @return Article
     */
    @Override
    public Article saveArticle(Article article) {
        setArticleParam(article);
        return saveEntity(article);
    }

    /**
     * 修改文章信息
     *
     * @param article 文章对象
     * @return Article
     */
    @Override
    public Article updateArticle(Article article) {
        return null;
    }

    /**
     * 根据文章Id获取文章的简单信息
     *
     * @param id 文章Id
     * @return Article 文章对象
     */
    @Override
    public Article getSimpleArticleById(Long id) {
        return null;
    }

    /**
     * 根据文章Id获取文章的全部信息
     *
     * @param id 文章Id
     * @return Article 文章对象
     */
    @Override
    public Article getFullArticleById(Long id) {
        return null;
    }

    /**
     * 根据文章Ids删除文章
     *
     * @param ids 文章Ids
     * @return int
     */
    @Override
    public int deleteArticlesByIds(String ids) {
        return 0;
    }

    /**
     * 注入真实实体类的mapper
     *
     * @return BaseMapper<T>
     */
    @Override
    public BaseMapper<Article, ArticleVO> getBaseMapper() {
        return articleMapper;
    }

    /**
     * 设置文章象参数
     *
     * @param article 文章对象
     * @return void
     */
    private void setArticleParam(Article article) {
        article.setCollectTimes(0);
        article.setViewTimes(0);
        article.setDeleted(false);
    }
}
