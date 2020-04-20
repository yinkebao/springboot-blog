package com.es.hfuu.plugin.blog.domain;

import com.es.hfuu.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName ArticleType
 * @Description 文章类型
 * @Author lsx
 * @Date 2019/12/17
 */
@ApiModel("文章类型对象ArticleType")
public class ArticleType extends BaseDomain{

    @ApiModelProperty(value = "英文名称", name = "eName")
    private String eName;
    @ApiModelProperty(value = "中文名称", name = "cName")
    private String cName;

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}
