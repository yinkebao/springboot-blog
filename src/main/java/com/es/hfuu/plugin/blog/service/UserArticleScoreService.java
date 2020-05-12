package com.es.hfuu.plugin.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.es.hfuu.plugin.blog.domain.UserArticleScore;

/**
 * @ClassName UserArticleScoreService
 * @Description
 * @Author ykb
 * @Date 2020/5/10
 */
public interface UserArticleScoreService extends IService<UserArticleScore> {

  Boolean ifExist(UserArticleScore score);

  void addScore(UserArticleScore score,Integer addScore);
}
