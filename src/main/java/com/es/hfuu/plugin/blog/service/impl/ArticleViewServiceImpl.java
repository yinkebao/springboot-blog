package com.es.hfuu.plugin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.es.hfuu.plugin.blog.domain.ArticleView;
import com.es.hfuu.plugin.blog.domain.UserArticleScore;
import com.es.hfuu.plugin.blog.mapper.ArticleViewMapper;
import com.es.hfuu.plugin.blog.service.ArticleViewService;
import com.es.hfuu.plugin.blog.service.UserArticleScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ArticleViewServiceImpl
 * @Description
 * @Author ykb
 * @Date 2020/4/20
 */
@Service
public class ArticleViewServiceImpl extends ServiceImpl<ArticleViewMapper, ArticleView> implements ArticleViewService {

  @Autowired
  private UserArticleScoreService userArticleScoreService;

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

  /**
   * 阅读
   *
   * @return Boolean
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean view(ArticleView articleView) {
    this.save(articleView);
    if (userArticleScoreService.ifExist(UserArticleScore.builder()
        .articleId(articleView.getArticleId())
        .userId(articleView.getUserId())
        .build())){
      userArticleScoreService.addScore(userArticleScoreService.getOne(new QueryWrapper<UserArticleScore>()
          .lambda()
          .eq(UserArticleScore::getUserId,articleView.getUserId())
          .eq(UserArticleScore::getArticleId,articleView.getArticleId())
      ),1);
    }
    return true;
  }
}
