package com.es.hfuu.common.idgenerator;

import com.es.hfuu.common.util.base.StringUtil;
import com.es.hfuu.common.util.exception.base.SystemUtilException;
import com.es.hfuu.common.util.web.IpAddressUtil;
import org.springframework.data.redis.core.ValueOperations;

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @ClassName IdGeneratorFactory
 * @Description id生成器工厂
 * @Author ykb
 * @Date 2019/11/8
 */
public class IdGeneratorFactory {
    /** id生成器自增key */
    private static final String ID_GENERATE_INCREMENT_KEY = "railcloud";

    /**
     * 生产id生成器
     * @Title: createIdGenerator
     * @param dataCenter 数据中心版本号（用于生成唯一主键，一般填写的机房id）
     * @param valueOperations redis 键值操作接口
     * @return IdGenerator
     */
    public IdGenerator createIdGenerator(String dataCenter, ValueOperations<String, Object> valueOperations) {
        if (StringUtil.isEmpty(dataCenter)) {
            throw new SystemUtilException("未配置数据中心id");
        }
        String currentIpAddress;
        try {
            currentIpAddress = IpAddressUtil.getLocalIP();
        } catch (UnknownHostException | SocketException e) {
            throw new SystemUtilException("获取本地ip异常", e);
        }
        Object result = valueOperations.get(currentIpAddress);
        if (result == null) {
            result = valueOperations.increment(ID_GENERATE_INCREMENT_KEY, 1);
            if (result == null) {
                throw new SystemUtilException("id生成服务异常");
            }
        }
        valueOperations.set(currentIpAddress,result);
        return new SnowflakeIdWorker(Long.parseLong(result.toString()), Long.parseLong(dataCenter));
    }
}
