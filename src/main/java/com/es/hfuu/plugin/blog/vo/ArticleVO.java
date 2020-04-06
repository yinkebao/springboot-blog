package com.es.hfuu.plugin.blog.vo;

import com.es.hfuu.common.vo.PagingVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ArticleVO
 * @Description
 * @Author ykb
 * @Date 2019/12/11
 */
@Data
@Slf4j
public class ArticleVO extends PagingVO {
    
    /** 标题 */
    private String title;
    /** 来源 0原创1转载 */
    private Integer source;
    /** 文章类型 */
    private Long articleTypeId;
    /** 是否发布 */
    private Boolean isPublish;
}
