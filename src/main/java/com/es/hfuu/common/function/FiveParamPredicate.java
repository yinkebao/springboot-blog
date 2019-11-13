package com.es.hfuu.common.function;

/**
 * @ClassName: FiveParamPredicate
 * @Description: 五参数断言函数接口
 * @author: ykb
 * @date: 2018/8/29
 */
@FunctionalInterface
public interface FiveParamPredicate<A, B, C, D, E> {

	/**
	 * 执行五参数断言型接口
	 * @Title: test
	 * @param a 参数1
	 * @param b 参数2
	 * @param c 参数3
	 * @param d 参数4
	 * @param e 参数5
	 * @return boolean
	 */
	boolean test(A a, B b, C c, D d, E e);
}
