package com.es.hfuu.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lsx
 * @className Swagger2Config
 * @description Swagger2配置类
 * @date 2019/11/8
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.es.hfuu.*.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo groupApiInfo(){
        return new ApiInfoBuilder()
                .title("lsx的博客")
                .description("lsx的博客")
                .termsOfServiceUrl("http://www.hztianque.com/")
                .contact(new Contact("lsx","http://www.hztianque.com/","yinkebao@hztianque.com"))
                .version("1.0")
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("lsx的博客")
                .description("lsx的博客")
                .termsOfServiceUrl("http://www.hztianque.com/")
                .contact(new Contact("lsx","http://www.hztianque.com/","yinkebao@hztianque.com"))
                .version("1.0")
                .build();
    }
}
