package com.es.hfuu.common.function;

/**
 * @ClassName: TwoParamFunction
 * @Description: 双参数函数
 * @author: ykb
 * @date: 2018/8/29
 */
@FunctionalInterface
public interface TwoParamFunction<A, B, R> {

	/**
	 * 执行双参数功能型接口
	 * @Title: apply
	 * @param a 参数1
	 * @param b 参数2
	 * @return R
	 */
	R apply(A a, B b);
}
