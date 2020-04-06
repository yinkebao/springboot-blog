package com.es.hfuu.common.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MybatisPlusConfig
 * @Description mybatis-plus配置
 * @Author lsx
 * @Date 2020/3/30
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

  /**
   * 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
   *
   * @return PaginationInterceptor
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor().setOverflow(true);
  }

  /**
   * 用于支持mybatis-plus逻辑删除
   *
   * @return ISqlInjector
   */
  @Bean
  public ISqlInjector sqlInjector() {
    return new LogicSqlInjector();
  }
}
