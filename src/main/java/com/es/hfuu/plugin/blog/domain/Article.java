package com.es.hfuu.plugin.blog.domain;

import com.es.hfuu.common.domain.BaseDomain;
import com.es.hfuu.plugin.user.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName Article
 * @Description 文章实体
 * @Author ykb
 * @Date 2019/12/11
 */
@ApiModel("文章对象Article")
public class Article extends BaseDomain {

    @ApiModelProperty(value = "标题", name = "title")
    private String title;

    @ApiModelProperty(value = "创建人id", name = "authorId")
    private Long authorId;

    @ApiModelProperty(value = "创建人（作者）", name = "author")
    private User author;

    @ApiModelProperty(value = "原作者", name = "originalAuthor")
    private String originalAuthor;

    @ApiModelProperty(value = "内容", name = "content")
    private String content;

    @ApiModelProperty(value = "文章类型id", name = "articleTypeId")
    private Long articleTypeId;

    @ApiModelProperty(value = "文章类型", name = "articleType")
    private ArticleType articleType;

    @ApiModelProperty(value = "来源（0原创/1转载）", name = "source")
    private Integer source;

    @ApiModelProperty(value = "原文链接", name = "sourceUrl")
    private String sourceUrl;

    @ApiModelProperty(value = "浏览次数", name = "viewTimes")
    private Integer viewTimes;

    @ApiModelProperty(value = "收藏次数", name = "collectTimes")
    private Integer collectTimes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(Long articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Integer getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(Integer viewTimes) {
        this.viewTimes = viewTimes;
    }

    public Integer getCollectTimes() {
        return collectTimes;
    }

    public void setCollectTimes(Integer collectTimes) {
        this.collectTimes = collectTimes;
    }
}
