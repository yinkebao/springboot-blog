package com.es.hfuu.plugin.blog.service.impl;

import com.es.hfuu.common.constants.Constants;
import com.es.hfuu.common.mapper.BaseMapper;
import com.es.hfuu.common.service.impl.BaseServiceImpl;
import com.es.hfuu.common.util.base.StringUtil;
import com.es.hfuu.plugin.blog.service.ArticleTypeService;
import com.es.hfuu.plugin.blog.vo.ArticleVO;
import com.es.hfuu.plugin.user.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.es.hfuu.plugin.blog.domain.Article;
import com.es.hfuu.plugin.blog.mapper.ArticleMapper;
import com.es.hfuu.plugin.blog.service.ArticleService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ArticleServiceImpl
 * @Description 文章服务层实现
 * @Author ykb
 * @Date 2019/12/11
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article, ArticleVO>
    implements ArticleService {

  @Autowired
  private ArticleMapper articleMapper;

  @Autowired
  private UserService userService;

  @Autowired
  private ArticleTypeService articleTypeService;

  /**
   * 获取分页列表
   *
   * @param articleVO 查询参数
   * @return Article
   */
  @Override
  public PageInfo<Article> listArticlesByParamForPage(ArticleVO articleVO) {
    articleVO.setRows(articleVO.getLimit());
    PageInfo<Article> pageInfo = listEntitiesForPageListByEntity(articleVO);
    for (Article article : pageInfo.getList()) {
      article.setArticleType(articleTypeService.getArticleTypeById(article.getArticleTypeId()));
      article.setAuthor(userService.getSimpleUserById(article.getAuthorId()));
    }
    return pageInfo;
  }

  /**
   * 获取普通列表
   *
   * @param articleVO 查询参数
   * @return List<Article>
   */
  @Override
  public PageInfo<Article> listArticlesByParam(ArticleVO articleVO) {
    articleVO.setIsPublish(true);
    articleVO.setRows(articleVO.getLimit());
    PageInfo<Article> articles = listEntitiesForPageListByEntity(articleVO);
    int startNum = 0;
    int endNum = 0;
    for (Article article : articles.getList()) {
      article.setAuthor(userService.getSimpleUserById(article.getAuthorId()));
      startNum = article.getContent().indexOf(Constants.LABEL_START);
      while (startNum != -1){
        endNum = article.getContent().indexOf(Constants.LABEL_END);
        article.setContent(article.getContent().replace(article.getContent().substring(startNum,endNum+1),""));
        startNum = article.getContent().indexOf(Constants.LABEL_START);
      }
      if (article.getContent().length() > 150){
        article.setContent(article.getContent().substring(0,150)+"...");
      }
    }
    return articles;
  }

  /**
   * 所有文章top5
   *
   * @return List<Article>
   */
  @Override
  public List<Article> listTopFiveArticles() {
    return articleMapper.listTopFiveArticles();
  }

  /**
   * 本周top3
   *
   * @return List<Article>
   */
  @Override
  public List<Article> listWeeFireArticles() {
    return articleMapper.listWeeFireArticles();
  }

  /**
   * 保存文章信息
   *
   * @param article 文章对象
   * @return Article
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public Article saveArticle(Article article) {
    setArticleParam(article);
    return saveEntity(article);
  }

  /**
   * 修改文章信息
   *
   * @param article 文章对象
   * @return Article
   */
  @Override
  public Article updateArticle(Article article) {
    return updateEntity(article);
  }

  /**
   * 发布文章
   *
   * @param id 文章id
   * @return Article
   */
  @Override
  public Article publishArticle(Long id) {
    articleMapper.publishArticle(id);
    return getEntityById(id);
  }

  /**
   * 下架文章
   *
   * @param id 文章id
   * @return Article
   */
  @Override
  public Article takeOffPublishArticle(Long id) {
    articleMapper.takeOffPublishArticle(id);
    return getEntityById(id);
  }

  /**
   * 根据文章Id获取文章的简单信息
   *
   * @param id 文章Id
   * @return Article 文章对象
   */
  @Override
  public Article getSimpleArticleById(Long id) {
    return getEntityById(id);
  }

  /**
   * 根据文章Id获取文章的全部信息
   *
   * @param id 文章Id
   * @return Article 文章对象
   */
  @Override
  public Article getFullArticleById(Long id) {
    Article article = getEntityById(id);
    article.setAuthor(userService.getSimpleUserById(article.getAuthorId()));
    article.setArticleType(articleTypeService.getArticleTypeById(article.getArticleTypeId()));
    return article;
  }

  /**
   * 根据文章Ids删除文章
   *
   * @param ids 文章Ids
   * @return int
   */
  @Override
  public int deleteArticlesByIds(String ids) {
    return deleteEntitiesByIds(ids);
  }

  /**
   * 注入真实实体类的mapper
   *
   * @return BaseMapper<T>
   */
  @Override
  public BaseMapper<Article, ArticleVO> getBaseMapper() {
    return articleMapper;
  }

  /**
   * 设置文章象参数
   *
   * @param article 文章对象
   * @return void
   */
  private void setArticleParam(Article article) {
    if (article.getSource().equals(0)) {
      article.setOriginalAuthor(null);
      article.setSourceUrl(null);
    }
    article.setCollectTimes(0);
    article.setViewTimes(0);
    article.setIsPublish(false);
    article.setIsDeleted(false);
  }
}
