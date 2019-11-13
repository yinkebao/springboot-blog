package com.es.hfuu.common.function;

/**
 * @ClassName: TwoParamPredicate
 * @Description: 双参数断言函数接口
 * @author: ykb
 * @date: 2018/8/29
 */
@FunctionalInterface
public interface TwoParamPredicate<A, B> {

	/**
	 * 执行双参数断言型接口
	 * @Title: test
	 * @param a 参数1
	 * @param b 参数2
	 * @return boolean
	 */
	boolean test(A a, B b);
}
