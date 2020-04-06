package com.es.hfuu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.es.hfuu.common.domain.AttachFile;
import com.es.hfuu.common.mapper.AttachFileMapper;
import com.es.hfuu.common.service.AttachFileService;
import org.springframework.stereotype.Service;

/**
 * @ClassName AttachFileServiceImpl
 * @Description 附件服务
 * @Author ykb
 * @Date 2020/3/22
 */
@Service("attachFileService")
public class AttachFileServiceImpl extends ServiceImpl<AttachFileMapper,AttachFile> implements AttachFileService {

  /**
   * 保存附件
   *
   * @param file 文件
   * @return AttachFile
   */
  @Override
  public AttachFile saveAttachFile(AttachFile file) {
    this.save(file);
    return this.getById(file.getId());
  }
}
