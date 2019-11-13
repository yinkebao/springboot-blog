package com.es.hfuu.common.util.redis.config;

import com.es.hfuu.common.domain.Session;
import com.es.hfuu.common.util.redis.serializer.FastJson2JsonRedisSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName SystemRedisConfig
 * @Description Redis的配置类
 * @Author ykb
 * @Date 2019/11/12
 */
@Configuration
public class SystemRedisConfig {

    /**
     * @Title: sessionRedisTemplate
     * @Description: 在本系统中操作Session使用的RedisTemplate，默认使用的FastJson2JsonRedisSerializer序列化。
     * 				 【注意：在shiro框架之外的地方使用Json的序列化，方便与后边使用redis客户端查看数据】
     * @param connectionFactory
     * @return RedisTemplate<String, Session>
     */
    @Bean(name = "sessionRedisTemplate")
    public RedisTemplate<String, Session> sessionRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Session> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(connectionFactory);

        //使用FastJson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        FastJson2JsonRedisSerializer fastJson2JsonRedisSerializer=new FastJson2JsonRedisSerializer(Session.class);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(fastJson2JsonRedisSerializer);
        template.setHashValueSerializer(fastJson2JsonRedisSerializer);
        template.setDefaultSerializer(new StringRedisSerializer());
        // 关闭事务
        template.setEnableTransactionSupport(false);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 实例化 HashOperations 对象,可以使用 Hash 类型操作
     *
     * @param sessionRedisTemplate
     * @return
     */
    @Bean(name = "hashOperations")
    public HashOperations<String, String, Session> hashOperations(@Qualifier("sessionRedisTemplate") RedisTemplate<String, Session> sessionRedisTemplate) {
        return sessionRedisTemplate.opsForHash();
    }

    /**
     * 实例化 ValueOperations 对象,可以使用 String 操作
     *
     * @param sessionRedisTemplate
     * @return
     */
    @Bean(name = "valueOperations")
    public ValueOperations<String, Session> valueOperations(@Qualifier("sessionRedisTemplate") RedisTemplate<String, Session> sessionRedisTemplate) {
        return sessionRedisTemplate.opsForValue();
    }

    /**
     * 实例化 ListOperations 对象,可以使用 List 操作
     *
     * @param sessionRedisTemplate
     * @return
     */
    @Bean(name = "listOperations")
    public ListOperations<String, Session> listOperations(@Qualifier("sessionRedisTemplate") RedisTemplate<String, Session> sessionRedisTemplate) {
        return sessionRedisTemplate.opsForList();
    }

    /**
     * 实例化 SetOperations 对象,可以使用 Set 操作
     *
     * @param sessionRedisTemplate
     * @return
     */
    @Bean(name = "setOperations")
    public SetOperations<String, Session> setOperations(@Qualifier("sessionRedisTemplate") RedisTemplate<String, Session> sessionRedisTemplate) {
        return sessionRedisTemplate.opsForSet();
    }

    /**
     * 实例化 ZSetOperations 对象,可以使用 ZSet 操作
     *
     * @param sessionRedisTemplate
     * @return
     */
    @Bean(name = "zSetOperations")
    public ZSetOperations<String, Session> zSetOperations(@Qualifier("sessionRedisTemplate") RedisTemplate<String, Session> sessionRedisTemplate) {
        return sessionRedisTemplate.opsForZSet();
    }

    /**
     * @Title: permissionRedisTemplate
     * @Description: 在本系统中操作Permisson使用的RedisTemplate，默认使用的FastJson2JsonRedisSerializer序列化。
     * 				 【注意：在shiro框架之外的地方使用Json的序列化，方便与后边使用redis客户端查看数据】
     * @param connectionFactory
     * @return RedisTemplate<String, Session>
     */
    @Bean(name = "permissionRedisTemplate")
    public RedisTemplate<String, String> permissionRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(connectionFactory);

        //使用FastJson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        FastJson2JsonRedisSerializer fastJson2JsonRedisSerializer=new FastJson2JsonRedisSerializer(String.class);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(fastJson2JsonRedisSerializer);
        template.setHashValueSerializer(fastJson2JsonRedisSerializer);
        template.setDefaultSerializer(new StringRedisSerializer());
        // 关闭事务
        template.setEnableTransactionSupport(false);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 实例化 SetOperations 对象,可以使用 Set 操作
     *
     * @param permissionRedisTemplate
     * @return
     */
    @Bean(name = "permissionSetOperations")
    public SetOperations<String, String> permissionSetOperations(@Qualifier("permissionRedisTemplate") RedisTemplate<String, String> permissionRedisTemplate) {
        return permissionRedisTemplate.opsForSet();
    }

    /**
     * @Title: objectRedisTemplate
     * @Description: 在本系统中操作Object使用的RedisTemplate，默认使用的FastJson2JsonRedisSerializer序列化。
     * 				 【注意：在shiro框架之外的地方使用Json的序列化，方便与后边使用redis客户端查看数据】
     * @param connectionFactory redis连接工厂
     * @return RedisTemplate<String, Session>
     */
    @Bean(name = "objectRedisTemplate")
    public RedisTemplate<String, Object> objectRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(connectionFactory);

        //使用FastJson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        FastJson2JsonRedisSerializer fastJson2JsonRedisSerializer=new FastJson2JsonRedisSerializer(Object.class);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(fastJson2JsonRedisSerializer);
        template.setHashValueSerializer(fastJson2JsonRedisSerializer);
        template.setDefaultSerializer(new StringRedisSerializer());
        template.setStringSerializer(new StringRedisSerializer());
        // 关闭事务
        template.setEnableTransactionSupport(false);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 实例化简单redis key-value 操作
     * @Title: objectValueOperations
     * @param objectRedisTemplate 通用redis操作模板
     * @return ValueOperations<String, Object> 简单redis key-value 操作
     */
    @Bean(name = "objectOperations")
    public ValueOperations<String, Object> objectValueOperations(@Qualifier("objectRedisTemplate") RedisTemplate<String, Object> objectRedisTemplate) {
        return objectRedisTemplate.opsForValue();
    }
}
