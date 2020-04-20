package com.es.hfuu.plugin.blog.domain;

import com.es.hfuu.common.domain.BaseDomain;
import com.es.hfuu.plugin.user.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName Article
 * @Description 文章实体
 * @Author lsx
 * @Date 2019/12/11
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @ApiModelProperty(value = "是否发布", name = "isPublish")
    private Boolean isPublish;

    @ApiModelProperty(value = "发布时间", name = "publishDate")
    private Date publishDate;

    @ApiModelProperty(value = "封面", name = "titlePage")
    private String titlePage;
}
