package com.es.hfuu.plugin.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.es.hfuu.plugin.blog.domain.ArticleCollect;

/**
 * @ClassName ArticleCollectService
 * @Description
 * @Author ykb
 * @Date 2020/4/20
 */
public interface ArticleCollectService extends IService<ArticleCollect> {

  /**
   * 是否收藏
   *
   * @param articleId 文章id
   * @param userId 用户id
   * @return Boolean
   */
  Boolean isCollected(Long articleId, Long userId);
}
