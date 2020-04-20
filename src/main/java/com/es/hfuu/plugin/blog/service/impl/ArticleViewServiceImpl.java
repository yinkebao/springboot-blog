package com.es.hfuu.plugin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.es.hfuu.plugin.blog.domain.ArticleView;
import com.es.hfuu.plugin.blog.mapper.ArticleViewMapper;
import com.es.hfuu.plugin.blog.service.ArticleViewService;
import org.springframework.stereotype.Service;

/**
 * @ClassName ArticleViewServiceImpl
 * @Description
 * @Author ykb
 * @Date 2020/4/20
 */
@Service
public class ArticleViewServiceImpl extends ServiceImpl<ArticleViewMapper, ArticleView> implements ArticleViewService {

  /**
   * 是否阅读
   *
   * @param articleId 文章id
   * @param userId 用户id
   * @return Boolean
   */
  @Override
  public Boolean isViewed(Long articleId, Long userId) {
    return 0 != this.count(new QueryWrapper<ArticleView>()
        .lambda()
        .eq(ArticleView::getArticleId,articleId)
        .eq(ArticleView::getUserId,userId)
    );
  }
}
