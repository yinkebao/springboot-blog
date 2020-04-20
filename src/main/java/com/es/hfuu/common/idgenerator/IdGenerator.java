package com.es.hfuu.common.idgenerator;

/**
 * @ClassName IdGenerator
 * @Description id生成器接口
 * @Author lsx
 * @Date 2019/11/8
 */
public interface IdGenerator {

    /**
     * 获取下一个id
     * @Title: getNextVal
     * @return Long id
     */
    Long getNextVal();
}
