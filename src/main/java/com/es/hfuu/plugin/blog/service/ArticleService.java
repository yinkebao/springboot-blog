package com.es.hfuu.plugin.blog.service;

import com.es.hfuu.plugin.blog.domain.Article;
import com.es.hfuu.plugin.blog.vo.ArticleVO;
import com.es.hfuu.plugin.blog.vo.MyArticleVo;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * @ClassName ArticleService
 * @Description 文章服务层接口
 * @Author lsx
 * @Date 2019/12/11
 */
public interface ArticleService {

  List<Article> recommend(Long userId);

  /**
   * 获取分页列表
   *
   * @param articleVO 查询参数
   * @return Article
   */
  PageInfo<Article> listArticlesByParamForPage(ArticleVO articleVO);

  /**
   * 获取普通列表
   *
   * @param articleVO 查询参数
   * @return List<Article>
   */
  PageInfo<Article> listArticlesByParam(ArticleVO articleVO);

  /**
   * 设置封面
   *
   * @param ids 文章id
   * @param titlePage 封面
   */
  void siteTitlePage(String titlePage, String ids);

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
   * 查询我收藏的文章
   *
   * @param userId 用户名
   * @return List<Article>
   */
  List<ArticleVO> listCollectArticles(Long userId);

  /**
   * 查询我的文章
   *
   * @param userId 用户名
   * @return List<Article>
   */
  List<ArticleVO> listMyArticles(Long userId);

  /**
   * 统计我的文章信息
   *
   * @param userId 用户名
   * @return MyArticleVo
   */
  MyArticleVo countMyArticleInfo(Long userId);


  /**
   * 保存文章信息
   *
   * @param article 文章对象
   * @return Article
   */
  Article saveArticle(Article article);

  /**
   * 修改文章信息
   *
   * @param article 文章对象
   * @return Article
   */
  Article updateArticle(Article article);

  /**
   * 发布文章
   *
   * @param id 文章id
   * @return Article
   */
  Article publishArticle(Long id);

  /**
   * 下架文章
   *
   * @param id 文章id
   * @return Article
   */
  Article takeOffPublishArticle(Long id);

  /**
   * 根据文章Id获取文章的简单信息
   *
   * @param id 文章Id
   * @return Article 文章对象
   */
  Article getSimpleArticleById(Long id);

  /**
   * 根据文章Id获取文章的全部信息
   *
   * @param id 文章Id
   * @return Article 文章对象
   */
  Article getFullArticleById(Long id);

  /**
   * 根据文章Ids删除文章
   *
   * @param ids 文章Ids
   * @return int
   */
  int deleteArticlesByIds(String ids);


}
