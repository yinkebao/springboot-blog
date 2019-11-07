package com.es.hfuu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value = "com.es.hfuu.mapper")
@SpringBootApplication
public class SpringbootWebRestfulcrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebRestfulcrudApplication.class, args);
    }

}
