package com.es.hfuu.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @className BaseDomain
 * @description 所有Domain类的基类
 * @author ykb
 * @date 2019/11/8
 **/
public class BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", name = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "数据添加人（userName）", name = "createUser", required = true)
    private String createUser;

    @ApiModelProperty(value = "数据添加日期", name = "createDate", required = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty(value = "数据修改人（userName）", name = "updateUser", required = true)
    private String updateUser;

    @ApiModelProperty(value = "数据修改日期", name = "updateDate", required = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty(value = "数据的状态(0:保存，1：删除)", name = "deleted")
    private Integer deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public int hashCode() {
        if (id != null) {
            final int prime = 31;
            int result = 1;
            result = prime * (prime * result + getClass().hashCode()) + id.hashCode();
            return result;
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BaseDomain)) {
            return false;
        }

        if ((getClass().isAssignableFrom(obj.getClass()))
                || (obj.getClass().isAssignableFrom(getClass()))) {

        } else {
            return false;
        }

        BaseDomain other = (BaseDomain) obj;
        if (other.getId() == null || getId() == null) {
            return false;
        } else {
            if (other.getId().equals(getId())) {
                return true;
            } else {
                return false;
            }
        }
    }
}
