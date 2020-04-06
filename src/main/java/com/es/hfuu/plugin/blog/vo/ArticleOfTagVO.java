package com.es.hfuu.plugin.blog.vo;

import com.es.hfuu.common.vo.PagingVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ArticleOfTagVo
 * @Description
 * @Author ykb
 * @Date 2020/3/22
 */
@Data
@Slf4j
public class ArticleOfTagVO extends PagingVO {

  @ApiModelProperty(value = "文章id", name = "articleId")
  private Long articleId;

  @ApiModelProperty(value = "标签id", name = "tagId")
  private Long tagId;
}
