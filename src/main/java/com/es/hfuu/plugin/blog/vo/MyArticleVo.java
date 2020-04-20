package com.es.hfuu.plugin.blog.vo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName MyArticleVo
 * @Description
 * @Author ykb
 * @Date 2020/4/20
 */
@Getter
@Setter
@NoArgsConstructor
public class MyArticleVo implements Serializable {

  @ApiModelProperty(value = "文章数量")
  private Integer articleNum;

  @ApiModelProperty(value = "浏览次数")
  private Integer viewTimes;

  @ApiModelProperty(value = "收藏次数")
  private Integer collectTimes;

  @Builder
  public MyArticleVo(Integer articleNum, Integer viewTimes, Integer collectTimes) {
    this.articleNum = articleNum;
    this.viewTimes = viewTimes;
    this.collectTimes = collectTimes;
  }
}
