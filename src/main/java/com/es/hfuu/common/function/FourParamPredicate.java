package com.es.hfuu.common.function;

/**
 * @ClassName: FourParamPredicate
 * @Description: 四参数断言函数接口
 * @author: ykb
 * @date: 2018/8/29
 */
@FunctionalInterface
public interface FourParamPredicate<A, B, C, D> {

	/**
	 * 执行四参数断言型接口
	 * @Title: test
	 * @param a 参数1
	 * @param b 参数2
	 * @param c 参数3
	 * @param d 参数4
	 * @return boolean
	 */
	boolean test(A a, B b, C c, D d);
}
