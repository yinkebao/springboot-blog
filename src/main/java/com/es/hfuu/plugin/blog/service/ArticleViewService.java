package com.es.hfuu.plugin.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.es.hfuu.plugin.blog.domain.ArticleView;

/**
 * @ClassName ArticleViewService
 * @Description
 * @Author ykb
 * @Date 2020/4/20
 */
public interface ArticleViewService extends IService<ArticleView> {

  /**
   * 是否阅读
   *
   * @param articleId 文章id
   * @param userId 用户id
   * @return Boolean
   */
  Boolean isViewed(Long articleId, Long userId);

  /**
   * 阅读
   *
   * @param articleView
   * @return Boolean
   */
  Boolean view(ArticleView articleView);
}
