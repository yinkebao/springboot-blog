package com.es.hfuu.plugin.blog.service.impl;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.requireNonNull;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.es.hfuu.common.constants.Constants;
import com.es.hfuu.common.mapper.BaseMapper;
import com.es.hfuu.common.service.impl.BaseServiceImpl;
import com.es.hfuu.plugin.blog.domain.ArticleCollect;
import com.es.hfuu.plugin.blog.domain.ArticleView;
import com.es.hfuu.plugin.blog.domain.UserArticleScore;
import com.es.hfuu.plugin.blog.service.ArticleCollectService;
import com.es.hfuu.plugin.blog.service.ArticleTypeService;
import com.es.hfuu.plugin.blog.service.ArticleViewService;
import com.es.hfuu.plugin.blog.service.UserArticleScoreService;
import com.es.hfuu.plugin.blog.vo.ArticleVO;
import com.es.hfuu.plugin.blog.vo.MyArticleVo;
import com.es.hfuu.plugin.user.service.UserService;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
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

  @Autowired
  private UserArticleScoreService userArticleScoreService;

  /**
   * 基于无物品的协同过滤推荐
   *
   * @param userId 用户id
   * @return Article>
   */
  @Override
  public List<Article> recommend(Long userId){

	try {
//    List<UserArticleScore> scores = userArticleScoreService.list();
//	FastByIDMap<PreferenceArray> preMap = new FastByIDMap<>();
//	PreferenceArray preferences = new GenericUserPreferenceArray(scores.size());
//	for (int i = 0; i < scores.size(); i++) {
//	  preferences.setUserID(i,scores.get(i).getUserId());
//	  preferences.setItemID(i,scores.get(i).getArticleId());
//	  preferences.setValue(i,scores.get(i).getScore());
//	}
//	preMap.put(3,preferences);
	  requireNonNull("请提供用户id", userId);
	  //构造数据连接池
	  MysqlDataSource dataSource = new MysqlDataSource();
	  dataSource.setUseSSL(true);
	  dataSource.setServerName("localhost");
	  dataSource.setPort(3306);
	  dataSource.setUser("root");
	  dataSource.setPassword("123456");
	  dataSource.setDatabaseName("ykb");
	  //读取数据表的方式构造数据模型
	  JDBCDataModel dataModel = new MySQLJDBCDataModel(dataSource, "user_article_score", "user_id", "article_id",
		  "score", "time");
//	DataModel dataModel = new GenericDataModel(preMap);
	/**
	 * 物品相似度定义
	 */
	//余弦相似度
	ItemSimilarity itemSimilarity = new UncenteredCosineSimilarity(dataModel);
	//欧几里得相似度
	//ItemSimilarity itemSimilarity= new EuclideanDistanceSimilarity(dataModel);
	//皮尔森相似度
	//ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
	//定义推荐引擎
	Recommender recommender =new GenericItemBasedRecommender(dataModel, itemSimilarity);
	//获取物品迭代器
	LongPrimitiveIterator itemIDIterator = dataModel.getItemIDs();
	//遍历所有物品
	while(itemIDIterator.hasNext()){
	  System.out.println("==========================================");
	  Long itermID=itemIDIterator.next();
	  LongPrimitiveIterator otherItemIDIterator=dataModel.getItemIDs();
	  //打印物品相似度
	  while (otherItemIDIterator.hasNext()){
		Long otherItermID=otherItemIDIterator.next();
		System.out.println("物品 "+itermID+" 与物品 "+otherItermID+" 的相似度为： "+itemSimilarity.itemSimilarity(itermID,otherItermID));
	  }
	}
	//获取用户迭代器
	LongPrimitiveIterator userIDIterator =dataModel.getUserIDs();
	//获取用户
//	Long userID=userIDIterator.next();
	//获取用户userID的推荐列表
	List<RecommendedItem> itemList= recommender.recommend(userId,5);
	List<Article> articles = new ArrayList<>();
	for(RecommendedItem item:itemList){
	  articles.add(articleMapper.getEntityById(item.getItemID()));
	  System.out.println("用户 "+userId+" 推荐物品 "+item.getItemID()+",物品评分 "+item.getValue());
	}
	return articles;
	} catch (Exception e) {
	  e.printStackTrace();
	  return null;
	}
  }

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
