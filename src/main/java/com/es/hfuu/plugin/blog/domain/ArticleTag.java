package com.es.hfuu.plugin.blog.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.es.hfuu.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ArticleTag
 * @Description
 * @Author ykb
 * @Date 2020/3/22
 */
@Data
@Slf4j
@ApiModel("文章标签对象ArticleTag")
@TableName("article_tag")
public class ArticleTag extends BaseDomain {

  @ApiModelProperty(value = "标签名", name = "tagName")
  @TableField("tag_name")
  private String tagName;
}
