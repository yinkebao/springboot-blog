package com.es.hfuu.plugin.blog.service.impl;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.requireValidString;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.es.hfuu.plugin.blog.domain.ArticleTag;
import com.es.hfuu.plugin.blog.mapper.ArticleTagMapper;
import com.es.hfuu.plugin.blog.service.ArticleTagService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ArticleTagServiceImpl
 * @Description
 * @Author ykb
 * @Date 2020/3/22
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService  {

  @Autowired
  private ArticleTagMapper articleTagMapper;

  public List<ArticleTag> listArticleTags(){
    return this.list();
  }

  /**
   * 保存标签信息
   *
   * @param tag 标签对象
   * @return ArticleTag
   */
  @Override
  public ArticleTag saveArticleTag(ArticleTag tag){
    this.save(tag);
    return getById(tag.getId());
  }

  /**
   * 修改标签信息
   *
   * @param tag 标签对象
   * @return ArticleTag
   */
  @Override
  public ArticleTag updateArticleTag(ArticleTag tag) {
    this.updateById(tag);
    return getById(tag.getId());
  }

  /**
   * 根据标签Ids删除标签
   *
   * @param ids 标签Ids
   * @return int
   */
  @Override
  public void deleteArticleTagsByIds(String ids) {
    requireValidString("请提供待删除的ids", ids);
    //将字符串的ids转换成Long类型的数组
    String[] idsStr = ids.replace(" ","").split(",");
    List<Long> idsLong = new ArrayList<>(idsStr.length);
    for (String s : idsStr) {
      idsLong.add(Long.parseLong(s));
    }
    this.removeByIds(idsLong);
  }

  /**
   * 批量新增
   *
   * @param tagList 标签集合
   */
  @Override
  public void saveArticleTags(List<ArticleTag> tagList){
    this.saveBatch(tagList);
  }

  /**
   * 根据名称获取
   *
   * @param name 标签名
   * @return ArticleTag
   */
  @Override
  public ArticleTag getByName(String name){
    return getOne(new QueryWrapper<ArticleTag>().eq("tag_name",name));
  }
}
