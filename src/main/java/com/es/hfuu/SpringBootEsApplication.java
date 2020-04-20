package com.es.hfuu;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author lsx
 * @description springboot启动类
 * @date 2019/9/8
 **/
@MapperScan(value = "com.es.hfuu.**.mapper")
@SpringBootApplication(scanBasePackages = "com.es.hfuu")
public class SpringBootEsApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootEsApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringBootEsApplication.class, args);
        LOGGER.info("ヾ(◍°∇°◍)ﾉﾞ    系统服务端启动成功      ヾ(◍°∇°◍)ﾉﾞ");
    }

}
