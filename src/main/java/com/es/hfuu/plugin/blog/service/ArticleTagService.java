package com.es.hfuu.plugin.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.es.hfuu.plugin.blog.domain.ArticleTag;
import java.util.List;

/**
 * @ClassName ArticleTagTagService
 * @Description
 * @Author ykb
 * @Date 2020/3/22
 */
public interface ArticleTagService extends IService<ArticleTag> {

  /**
   * 保存标签信息
   *
   * @param article 标签对象
   * @return ArticleTag
   */
  ArticleTag saveArticleTag(ArticleTag article);

  /**
   * 修改标签信息
   *
   * @param article 标签对象
   * @return ArticleTag
   */
  ArticleTag updateArticleTag(ArticleTag article);

  /**
   * 根据标签Ids删除标签
   *
   * @param ids 标签Ids
   */
  void deleteArticleTagsByIds(String ids);

  /**
   * 批量新增
   *
   * @param tagList 标签集合
   */
  void saveArticleTags(List<ArticleTag> tagList);

  /**
   * 根据名称获取
   *
   * @param name 标签名
   * @return ArticleTag
   */
  ArticleTag getByName(String name);
}
