package com.es.hfuu.plugin.blog.mapper;

import com.es.hfuu.common.mapper.BaseMapper;
import com.es.hfuu.plugin.blog.domain.ArticleType;
import com.es.hfuu.plugin.blog.vo.ArticleVO;

import java.util.List;

/**
 * @ClassName ArticleTypeMapper
 * @Description 文章类型Mapper接口
 * @Author lsx
 * @Date 2019/12/17
 */
public interface ArticleTypeMapper extends BaseMapper<ArticleType, ArticleVO> {

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
}
