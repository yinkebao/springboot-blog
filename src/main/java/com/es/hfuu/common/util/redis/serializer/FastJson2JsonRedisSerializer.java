package com.es.hfuu.common.util.redis.serializer;

import com.alibaba.fastjson.parser.ParserConfig;
import com.es.hfuu.common.util.exception.util.FastJsonUtil;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.util.Optional;

/**
 * @ClassName FastJson2JsonRedisSerializer
 * @Description
 * @Author ykb
 * @Date 2019/11/12
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private Class<T> clazz;

    public FastJson2JsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }
    static {
        // 参考地址：https://blog.csdn.net/cdyjy_litao/article/details/72458538
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        //解决fastJson autoType is not support错误。如果有多个包名前缀，分多次addAccept
		/*ParserConfig.getGlobalInstance().addAccept("org.apache.shiro");
		ParserConfig.getGlobalInstance().addAccept("com.tianque.usercenter.domain");*/
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        return Optional.ofNullable(t)
                .map(r -> FastJsonUtil.toJsonStr(t).getBytes(DEFAULT_CHARSET))
                .orElseGet(() -> new byte[0]);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {

        return Optional.ofNullable(bytes)
                .map(t -> FastJsonUtil.parseObject(new String(t, DEFAULT_CHARSET), clazz))
                .orElseGet(() -> null);
    }
}
