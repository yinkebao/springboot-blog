package com.es.hfuu.plugin.blog.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName ArticleTagBindComment
 * @Description
 * @Author ykb
 * @Date 2020/4/20
 */
@ApiModel
@Getter
@Setter
@NoArgsConstructor
public class ArticleTagBindComment implements Serializable {

  @ApiModelProperty(value = "文章id", example = "232")
  private Long articleId;

  @ApiModelProperty(value = "标签ids", example = "232")
  private List<Long> tagIds;
}
