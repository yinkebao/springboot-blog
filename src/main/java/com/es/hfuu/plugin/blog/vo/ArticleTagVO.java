package com.es.hfuu.plugin.blog.vo;

import com.es.hfuu.common.vo.PagingVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ArticleTagVo
 * @Description
 * @Author ykb
 * @Date 2020/3/22
 */
@Data
@Slf4j
public class ArticleTagVO extends PagingVO {

  private Long id;

  private String tagName;
}
