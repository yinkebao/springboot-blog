package com.es.hfuu.common.aspect;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.sql.Timestamp;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName MetaHandler
 * @Description 处理新增和更新的基础数据填充,配合BaseEntity和MyBatisPlusConfig使用
 * @Author ykb
 * @Date 2019/12/12
 */
@Component
public class MetaHandler implements MetaObjectHandler {

  private static final Logger logger = LoggerFactory.getLogger(MetaHandler.class);

  /**
   * 新增数据执行
   */
  @Override
  public void insertFill(MetaObject metaObject) {
    this.setFieldValByName("createDate", new Timestamp(System.currentTimeMillis()), metaObject);
    this.setFieldValByName("createUser", "lsx", metaObject);
  }

  /**
   * 更新数据执行
   */
  @Override
  public void updateFill(MetaObject metaObject) {
    this.setFieldValByName("updateDate", new Timestamp(System.currentTimeMillis()), metaObject);
    this.setFieldValByName("updateUser", "lsx",
        metaObject);
  }

}
