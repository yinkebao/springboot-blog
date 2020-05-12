package com.es.hfuu.plugin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.es.hfuu.plugin.blog.domain.UserArticleScore;
import com.es.hfuu.plugin.blog.mapper.UserArticleScoreMapper;
import com.es.hfuu.plugin.blog.service.UserArticleScoreService;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserArticleScoreServiceImpl
 * @Description
 * @Author ykb
 * @Date 2020/5/10
 */
@Service("userArticleScoreService")
public class UserArticleScoreServiceImpl extends
	ServiceImpl<UserArticleScoreMapper, UserArticleScore> implements UserArticleScoreService {

  @Override
  public Boolean ifExist(UserArticleScore score) {
	return this.getOne(new QueryWrapper<UserArticleScore>()
		.lambda()
		.eq(UserArticleScore::getUserId,score.getUserId())
		.eq(UserArticleScore::getArticleId,score.getArticleId())
	) != null;
  }

  @Override
  public void addScore(UserArticleScore score, Integer addScore) {
    score.setScore(score.getScore()+addScore);
    this.updateById(score);
  }
}
