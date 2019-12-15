package com.es.hfuu.plugin.blog.mapper;

import com.es.hfuu.common.mapper.BaseMapper;
import com.es.hfuu.plugin.blog.vo.ArticleVO;
import com.es.hfuu.plugin.blog.domain.Article;


/**
 * @ClassName ArticleMapper
 * @Description 文章mapper接口
 * @Author ykb
 * @Date 2019/12/11
 */
public interface ArticleMapper extends BaseMapper<Article, ArticleVO> {


    /**
     * 根据文章Id获取文章信息
     * @Title: getSimpleArticleById
     * @param id 文章Id
     * @return Article 文章对象
     */
    Article getSimpleArticleById(Long id);
}
