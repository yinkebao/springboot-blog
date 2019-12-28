package com.es.hfuu.plugin.blog.service.impl;

import com.es.hfuu.common.mapper.BaseMapper;
import com.es.hfuu.common.service.impl.BaseServiceImpl;
import com.es.hfuu.plugin.blog.domain.ArticleType;
import com.es.hfuu.plugin.blog.mapper.ArticleTypeMapper;
import com.es.hfuu.plugin.blog.service.ArticleTypeService;
import com.es.hfuu.plugin.blog.vo.ArticleTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.dbInvokeFunction;
import static com.es.hfuu.common.util.exception.util.ExceptionUtil.requireValidString;

/**
 * @ClassName ArticleTypeServiceImpl
 * @Description 文章类型接口实现
 * @Author ykb
 * @Date 2019/12/17
 */
@Service
public class ArticleTypeServiceImpl extends BaseServiceImpl<ArticleType, ArticleTypeVO> implements ArticleTypeService {

    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    /**
     * 获取列表
     *
     * @return List<ArticleType>
     */
    @Override
    public List<ArticleType> listArticleTypes() {
        return articleTypeMapper.listArticleTypes();
    }

    /**
     * 根据id获取
     *
     * @param id id
     * @return ArticleType
     */
    @Override
    public ArticleType getArticleTypeById(Long id) {
        return articleTypeMapper.getArticleTypeById(id);
    }

    /**
     * 保存文章类型
     *
     * @param articleType 文章类型对象
     * @return ArticleType
     */
    @Override
    public ArticleType saveArticleType(ArticleType articleType) {
        return saveEntity(articleType);
    }

    /**
     * 修改文章类型
     *
     * @param articleType 文章对象
     * @return Article
     */
    @Override
    public ArticleType updateArticleType(ArticleType articleType) {
        return updateEntity(articleType);
    }

    /**
     * 根据文章Ids删除文章类型
     *
     * @param ids 文章Ids
     * @return int
     */
    @Override
    public int deleteArticleTypesByIds(String ids) {
        return deleteEntitiesByIds(ids);
    }

    /**
     * 注入真实实体类的mapper
     *
     * @return BaseMapper<T>
     * @Title: getBaseMapper
     */
    @Override
    public BaseMapper getBaseMapper() {
        return articleTypeMapper;
    }
}
