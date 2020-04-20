package com.es.hfuu.plugin.role.domain;

import com.es.hfuu.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lsx
 * @className Role
 * @description 角色
 * @date 2019/11/8
 **/
@ApiModel("角色对象Role")
public class Role extends BaseDomain {

    @ApiModelProperty(value = "角色名称", name = "roleName")
    private String roleName;

    @ApiModelProperty(value = "角色描述", name = "description")
    private String description;


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
