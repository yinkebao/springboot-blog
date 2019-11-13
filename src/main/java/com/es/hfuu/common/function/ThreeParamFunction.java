package com.es.hfuu.common.function;

/**
 * @ClassName: ThreeParamFunction
 * @Description: 三参数函数
 * @author: ykb
 * @date: 2018/8/29
 */
@FunctionalInterface
public interface ThreeParamFunction<A, B, C, R> {

	/**
	 * 执行三参数功能型接口
	 * @Title: apply
	 * @param a 参数1
	 * @param b 参数2
	 * @param c 参数3
	 * @return R
	 */
	R apply(A a, B b, C c);
}
