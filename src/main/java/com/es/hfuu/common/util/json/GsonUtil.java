package com.es.hfuu.common.util.json;

import com.google.gson.Gson;

/**
 * @ClassName GsonUtil
 * @Description 谷歌的Json工具类
 * @Author ykb
 * @Date 2019/12/5
 */
public class GsonUtil {
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}
