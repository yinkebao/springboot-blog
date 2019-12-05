package com.es.hfuu.common.util.redis.serializer;

import com.es.hfuu.common.util.json.GsonUtil;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.util.Optional;

/**
 * @ClassName GsonSerializer
 * @Description 用google的Gson来实现Redis的序列化
 * @Author ykb
 * @Date 2019/12/5
 */
public class GsonSerializer<T> implements RedisSerializer<T> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private Class<T> clazz;

    public GsonSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        return Optional.ofNullable(t)
                .map(r -> GsonUtil.toJson(t).getBytes(DEFAULT_CHARSET))
                .orElseGet(() -> new byte[0]);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {

        return Optional.ofNullable(bytes)
                .map(t -> GsonUtil.toObject(new String(t, DEFAULT_CHARSET), clazz))
                .orElseGet(() -> null);
    }
}
