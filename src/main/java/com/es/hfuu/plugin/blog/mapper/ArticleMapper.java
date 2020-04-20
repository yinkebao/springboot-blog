package com.es.hfuu.plugin.blog.mapper;

import com.es.hfuu.common.mapper.BaseMapper;
import com.es.hfuu.plugin.blog.vo.ArticleVO;
import com.es.hfuu.plugin.blog.domain.Article;
import java.util.List;
import org.apache.ibatis.annotations.Param;


/**
 * @ClassName ArticleMapper
 * @Description 文章mapper接口
 * @Author lsx
 * @Date 2019/12/11
 */
public interface ArticleMapper extends BaseMapper<Article, ArticleVO> {

  /**
   * 发布文章
   *
   * @param id 文章id
   */
  void publishArticle(Long id);

  /**
   * 设置封面
   *
   * @param ids 文章id
   * @param titlePage 封面
   */
  void siteTitlePage(@Param("titlePage") String titlePage, @Param("ids") List<Long> ids);

  /**
   * 下架文章
   *
   * @param id 文章id
   */
  void takeOffPublishArticle(Long id);

  /**
   * 所有文章top5
   *
   * @return List<Article>
   */
  List<Article> listTopFiveArticles();

  /**
   * 本周top3
   *
   * @return List<Article>
   */
  List<Article> listWeeFireArticles();

  /**
   * 查询我的文章
   *
   * @param userId 用户名
   * @return List<Article>
   */
  List<Article> listMyArticles(@Param("userId") Long userId);

  /**
   * 查询我的文章
   *
   * @param userId 用户名
   * @return List<Article>
   */
  List<Article> listCollectArticles(@Param("userId") Long userId);

  /**
   * 阅读文章
   *
   * @param id 文章id
   */
  void readArticle(@Param("id") String id);

  /**
   * 我的文章收藏数
   *
   * @param userId 用户名
   * @return Integer
   */
  Integer countCollectTimes(@Param("userId") Long userId);

  /**
   * 我的文章浏览数
   *
   * @param userId 用户名
   * @return Integer
   */
  Integer countViewTimes(@Param("userId") Long userId);

  /**
   * countMyArticleNum
   *
   * @param userId 用户名
   * @return Integer
   */
  Integer countMyArticleNum(@Param("userId") Long userId);
}
