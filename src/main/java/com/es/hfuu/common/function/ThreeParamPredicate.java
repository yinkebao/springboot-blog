package com.es.hfuu.common.function;

/**
 * @ClassName: ThreeParamPredicate
 * @Description: 三参数断言函数接口
 * @author: ykb
 * @date: 2018/8/29
 */
@FunctionalInterface
public interface ThreeParamPredicate<A, B, C> {

	/**
	 * 执行三参数断言型接口
	 * @Title: test
	 * @param a 参数1
	 * @param b 参数2
	 * @param c 参数3
	 * @return boolean
	 */
	boolean test(A a, B b, C c);
}
