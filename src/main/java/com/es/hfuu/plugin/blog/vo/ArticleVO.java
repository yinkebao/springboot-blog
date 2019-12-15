package com.es.hfuu.plugin.blog.vo;

import com.es.hfuu.common.vo.PagingVO;

/**
 * @ClassName ArticleVO
 * @Description
 * @Author ykb
 * @Date 2019/12/11
 */
public class ArticleVO extends PagingVO {
    
    /** 标题 */
    private String title;
    /** 来源 0原创1转载 */
    private Integer source;
    /** 博客类型 */
    private Long blogTypeId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Long getBlogTypeId() {
        return blogTypeId;
    }

    public void setBlogTypeId(Long blogTypeId) {
        this.blogTypeId = blogTypeId;
    }
}
