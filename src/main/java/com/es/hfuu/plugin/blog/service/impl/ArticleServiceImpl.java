package com.es.hfuu.plugin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.es.hfuu.common.constants.Constants;
import com.es.hfuu.common.mapper.BaseMapper;
import com.es.hfuu.common.service.impl.BaseServiceImpl;
import com.es.hfuu.plugin.blog.domain.ArticleCollect;
import com.es.hfuu.plugin.blog.domain.ArticleView;
import com.es.hfuu.plugin.blog.service.ArticleCollectService;
import com.es.hfuu.plugin.blog.service.ArticleTypeService;
import com.es.hfuu.plugin.blog.service.ArticleViewService;
import com.es.hfuu.plugin.blog.vo.ArticleVO;
import com.es.hfuu.plugin.blog.vo.MyArticleVo;
import com.es.hfuu.plugin.user.service.UserService;
import com.github.pagehelper.PageInfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.es.hfuu.plugin.blog.domain.Article;
import com.es.hfuu.plugin.blog.mapper.ArticleMapper;
import com.es.hfuu.plugin.blog.service.ArticleService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ArticleServiceImpl
 * @Description 文章服务层实现
 * @Author lsx
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

  @Autowired
  private ArticleCollectService articleCollectService;

  @Autowired
  private ArticleViewService articleViewService;

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
      while (startNum != -1) {
        endNum = article.getContent().indexOf(Constants.LABEL_END);
        article.setContent(
            article.getContent().replace(article.getContent().substring(startNum, endNum + 1), ""));
        startNum = article.getContent().indexOf(Constants.LABEL_START);
      }
      if (article.getContent().length() > 150) {
        article.setContent(article.getContent().substring(0, 150) + "...");
      }
    }
    return articles;
  }

  /**
   * 设置封面
   *
   * @param titlePage 封面
   * @param ids 文章id
   */
  @Override
  public void siteTitlePage(String titlePage, String ids) {
    //将字符串的ids转换成Long类型的数组
    String[] idsStr = ids.replace(" ","").split(",");
    List<Long> idsLong = new ArrayList<>(idsStr.length);
    for (String s : idsStr) {
      idsLong.add(Long.parseLong(s));
    }
    articleMapper.siteTitlePage(titlePage,idsLong);
  }

  /**
   * 所有文章top5
   *
   * @return List<Article>
   */
  @Override
  public List<Article> listTopFiveArticles() {
    List<Article> articles = articleMapper.listTopFiveArticles();
    articles.forEach(article ->
        article.setTitle(
            article.getTitle().length() > 15 ? article.getTitle().substring(0, 14) + "..."
                : article.getTitle())
    );
    return articles;
  }

  /**
   * 本周top3
   *
   * @return List<Article>
   */
  @Override
  public List<Article> listWeeFireArticles() {
    List<Article> articles = articleMapper.listWeeFireArticles();
    articles.forEach(article ->
        article.setTitle(
            article.getTitle().length() > 15 ? article.getTitle().substring(0, 14) + "..."
                : article.getTitle())
    );
    return articles;
  }

  /**
   * 我的文章
   *
   * @return List<Article>
   */
  @Override
  public List<ArticleVO> listMyArticles(Long userId) {
    List<Article> articles = articleMapper.listMyArticles(userId);
    List<ArticleVO> articleVOS = new ArrayList<>();
    articles.forEach(article ->{
      articleVOS.add(ArticleVO.builder()
          .title(article.getTitle().length() > 16 ? article.getTitle().substring(0, 15) + "..."
              : article.getTitle())
          .id(article.getId())
          .publicDateStr(new SimpleDateFormat("yyyy-MM-dd").format(article.getPublishDate()))
          .source(article.getSource())
          .build());
        }
    );
    return articleVOS;
  }

  /**
   * 我收藏的文章
   *
   * @return List<Article>
   */
  @Override
  public List<ArticleVO> listCollectArticles(Long userId) {
    List<Article> articles = articleMapper.listCollectArticles(userId);
    List<ArticleVO> articleVOS = new ArrayList<>();
    articles.forEach(article ->{
          articleVOS.add(ArticleVO.builder()
              .title(article.getTitle().length() > 16 ? article.getTitle().substring(0, 15) + "..."
                  : article.getTitle())
              .id(article.getId())
              .publicDateStr(new SimpleDateFormat("yyyy-MM-dd").format(article.getPublishDate()))
              .source(article.getSource())
              .build());
        }
    );
    return articleVOS;
  }

  /**
   * 统计我的文章信息
   *
   * @param userId 用户id
   * @return MyArticleVo
   */
  @Override
  public MyArticleVo countMyArticleInfo(Long userId) {
    return MyArticleVo.builder()
        .articleNum(articleMapper.countMyArticleNum(userId))
        .viewTimes(articleViewService.count(new QueryWrapper<ArticleView>()
            .lambda()
            .eq(ArticleView::getUserId,userId)
        ))
        .collectTimes(articleCollectService.count(new QueryWrapper<ArticleCollect>()
            .lambda()
            .eq(ArticleCollect::getUserId,userId)))
        .build();
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
