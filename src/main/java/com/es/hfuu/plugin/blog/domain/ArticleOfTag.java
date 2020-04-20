package com.es.hfuu.plugin.blog.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.es.hfuu.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ArticleOfTag
 * @Description
 * @Author ykb
 * @Date 2020/3/22
 */
@Data
@Slf4j
@ApiModel("文章与标签关系ArticleOfTag")
@TableName("article_of_tag")
public class ArticleOfTag extends BaseDomain {

  @ApiModelProperty(value = "文章id", name = "articleId")
  @TableField("article_id")
  private Long articleId;

  @ApiModelProperty(value = "标签id", name = "tagId")
  @TableField("tag_id")
  private Long tagId;

}
