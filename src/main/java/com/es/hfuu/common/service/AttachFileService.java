package com.es.hfuu.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.es.hfuu.common.domain.AttachFile;

/**
 * @ClassName AttachFileService
 * @Description 附件接口
 * @Author ykb
 * @Date 2020/3/22
 */
public interface AttachFileService extends IService<AttachFile> {

  /**
   * 保存附件
   *
   * @param file 文件
   * @return AttachFile
   */
  AttachFile saveAttachFile(AttachFile file);
}
