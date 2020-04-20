package com.es.hfuu.plugin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.es.hfuu.plugin.blog.domain.ArticleCollect;
import com.es.hfuu.plugin.blog.mapper.ArticleCollectMapper;
import com.es.hfuu.plugin.blog.service.ArticleCollectService;
import org.springframework.stereotype.Service;

/**
 * @ClassName ArticleCollectServiceImpl
 * @Description
 * @Author ykb
 * @Date 2020/4/20
 */
@Service("articleCollectService")
public class ArticleCollectServiceImpl extends ServiceImpl<ArticleCollectMapper, ArticleCollect>
    implements ArticleCollectService {

  /**
   * 是否收藏
   *
   * @param articleId 文章id
   * @param userId 用户id
   * @return Boolean true是false否
   */
  @Override
  public Boolean isCollected(Long articleId, Long userId) {
    return 0 != this.count(new QueryWrapper<ArticleCollect>()
        .lambda()
        .eq(ArticleCollect::getArticleId,articleId)
        .eq(ArticleCollect::getUserId,userId)
    );
  }
}
