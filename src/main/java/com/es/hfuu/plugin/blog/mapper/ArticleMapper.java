package com.es.hfuu.plugin.blog.mapper;

import com.es.hfuu.common.mapper.BaseMapper;
import com.es.hfuu.plugin.blog.vo.ArticleVO;
import com.es.hfuu.plugin.blog.domain.Article;
import java.util.List;


/**
 * @ClassName ArticleMapper
 * @Description 文章mapper接口
 * @Author ykb
 * @Date 2019/12/11
 */
public interface ArticleMapper extends BaseMapper<Article, ArticleVO> {

    /**
     * 发布文章
     *
     * @param id 文章id
     */
    void publishArticle(Long id);

    /**
     * 下架文章
     *
     * @param id 文章id
     */
    void takeOffPublishArticle(Long id);

    /**
     * 所有文章top5
     *
     * @return List<Article>
     */
    List<Article> listTopFiveArticles();

    /**
     * 本周top3
     *
     * @return List<Article>
     */
    List<Article> listWeeFireArticles();
}
