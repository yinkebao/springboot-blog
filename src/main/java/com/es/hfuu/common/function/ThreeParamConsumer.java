package com.es.hfuu.common.function;

/**
 * @ClassName: ThreeParamConsumer
 * @Description: 三参数消费函数接口
 * @author: ykb
 * @date: 2018/8/29
 */
@FunctionalInterface
public interface ThreeParamConsumer<A, B, C> {

	/**
	 * 执行三参数消费型接口
	 * @Title: accept
	 * @param a 参数1
	 * @param b 参数2
	 * @param c 参数3
	 * @return void
	 */
	void accept(A a, B b, C c);
}
