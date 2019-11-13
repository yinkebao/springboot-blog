package com.es.hfuu.common.config;

import com.es.hfuu.common.idgenerator.IdGenerator;
import com.es.hfuu.common.idgenerator.IdGeneratorFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @ClassName IdGeneratorConfig
 * @Description 分布式id生成器初始化
 * @Author ykb
 * @Date 2019/11/8
 */
@Configuration
public class IdGeneratorConfig {

    /** id（用于生成唯一主键，一般填写的机房id） */
    @Value("${springboot.es.datacenter.version}")
    private String dataCenter;

    @Bean
    public IdGenerator createIdGenerator(ValueOperations<String, Object> valueOperations) {
        return new IdGeneratorFactory().createIdGenerator(dataCenter, valueOperations);
    }

}
