package com.es.hfuu.common.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName AttachFile
 * @Description
 * @Author ykb
 * @Date 2020/3/22
 */
@Data
@Slf4j
@ApiModel("文章与标签关系ArticleOfTag")
@TableName("attach_files")
public class AttachFile extends BaseDomain {

  /** 附件名称 */
  @ApiModelProperty(value = "附件名", name = "fileName")
  @TableField("file_name")
  private String fileName;
  /** 附件的物理路径 */
  @TableField("physics_file_url")
  private String physicsFileUrl;
  /** 附件所属模块 */
  @TableField("module_key")
  private String moduleKey;
  /** 模块附件关联表中的id */
  @TableField("module_object_id")
  private Long moduleObjectId;

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getPhysicsFileUrl() {
    return physicsFileUrl;
  }

  public void setPhysicsFileUrl(String physicsFileUrl) {
    this.physicsFileUrl = physicsFileUrl;
  }

  public String getModuleKey() {
    return moduleKey;
  }

  public void setModuleKey(String moduleKey) {
    this.moduleKey = moduleKey;
  }

  public Long getModuleObjectId() {
    return moduleObjectId;
  }

  public void setModuleObjectId(Long moduleObjectId) {
    this.moduleObjectId = moduleObjectId;
  }
}
