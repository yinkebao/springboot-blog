package com.es.hfuu.plugin.blog.service;

import com.es.hfuu.plugin.blog.domain.ArticleType;

import java.util.List;

/**
 * @ClassName ArticleTypeService
 * @Description 文章类型接口
 * @Author ykb
 * @Date 2019/12/17
 */
public interface ArticleTypeService {

    /**
     * 获取列表
     *
     * @return List<ArticleType>
     */
    List<ArticleType> listArticleTypes();

    /**
     * 根据id获取
     *
     * @param id id
     * @return ArticleType
     */
    ArticleType getArticleTypeById(Long id);

    /**
     * 保存文章类型
     *
     * @param articleType 文章类型对象
     * @return ArticleType
     */
    ArticleType saveArticleType(ArticleType articleType);

    /**
     * 修改文章类型
     *
     * @param articleType 文章对象
     * @return Article
     */
    ArticleType updateArticleType(ArticleType articleType);

    /**
     * 根据文章Ids删除文章类型
     *
     * @param ids 文章Ids
     * @return int
     */
    int deleteArticleTypesByIds(String ids);
}
