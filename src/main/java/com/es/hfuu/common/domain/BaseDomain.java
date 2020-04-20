package com.es.hfuu.common.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @className BaseDomain
 * @description 所有Domain类的基类
 * @author lsx
 * @date 2019/11/8
 **/
@NoArgsConstructor
@AllArgsConstructor
public class BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", name = "id", required = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "数据添加人（userName）", name = "createUser", required = true)
    @TableField(value = "create_user",fill = FieldFill.INSERT)
    private String createUser;

    @ApiModelProperty(value = "数据添加日期", name = "createDate", required = true)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_date",fill = FieldFill.INSERT_UPDATE)
    private Date createDate;

    @ApiModelProperty(value = "数据修改人（userName）", name = "updateUser", required = true)
    @TableField(value = "update_user")
    private String updateUser;

    @ApiModelProperty(value = "数据修改日期", name = "updateDate", required = true)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_date",fill = FieldFill.UPDATE)
    private Date updateDate;

    @ApiModelProperty(value = "数据的状态(删除/未删除)", name = "isDelete")
    @TableLogic
    private Boolean isDeleted;

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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
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
