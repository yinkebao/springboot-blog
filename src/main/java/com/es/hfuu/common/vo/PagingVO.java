package com.es.hfuu.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName PagingVO
 * @Description 分页查询基类信息
 * @Author ykb
 * @Date 2019/11/8
 */
@ApiModel("分页查询基类PagingVO")
public class PagingVO implements Serializable {
    private static final long serialVersionUID = -6664749560186553230L;

    @ApiModelProperty(value = "页码", name = "page",required = true)
    private Integer page;
    @ApiModelProperty(value = "每页条数", name = "rows",required = true)
    private Integer rows;
    @ApiModelProperty(value = "排序字段", name = "sidx",required = true)
    private String sidx;
    @ApiModelProperty(value = "排序方式", name = "sord",required = true)
    private String sord;

    public Integer getPage() {
        return page == null ? 1 : page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows == null ? 20 : rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    @Override
    public String toString() {
        return "PagingVO{" +
                "page=" + page +
                ", rows=" + rows +
                ", sidx='" + sidx + '\'' +
                ", sord='" + sord + '\'' +
                '}';
    }
}
