package com.es.hfuu.plugin.blog.domain;

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
public class ArticleOfTag extends BaseDomain {

  @ApiModelProperty(value = "文章id", name = "articleId")
  private Long articleId;

  @ApiModelProperty(value = "标签id", name = "tagId")
  private Long tagId;

}
