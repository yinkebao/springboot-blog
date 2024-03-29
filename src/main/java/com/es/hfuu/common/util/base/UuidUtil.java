package com.es.hfuu.common.util.base;

import java.util.UUID;
/**
 * @ClassName UuidUtil
 * @Description 封装JDK自带的UUID, 全部转大写, 中间无-分割
 * @author lsx
 * @date 2019/11/7
 */
public class UuidUtil {
	public static String uuid() {
		return UUID.randomUUID().toString().toUpperCase().replace("-", "");
	}
}
