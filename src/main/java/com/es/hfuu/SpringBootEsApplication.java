package com.es.hfuu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author ykb
 * @description springboot启动类
 * @date 2019/9/8
 **/
@MapperScan(value = "com.es.hfuu.**.mapper")
@SpringBootApplication(scanBasePackages = "com.es.hfuu")
public class SpringBootEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEsApplication.class, args);
    }

}
