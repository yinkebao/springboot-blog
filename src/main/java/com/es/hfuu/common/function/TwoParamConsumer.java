package com.es.hfuu.common.function;

/**
 * @ClassName: TwoParamConsumer
 * @Description: 双参数消费函数接口
 * @author: ykb
 * @date: 2018/8/29
 */
@FunctionalInterface
public interface TwoParamConsumer<A, B> {

	/**
	 * 执行双参数消费型接口
	 * @Title: accept
	 * @param a 参数1
	 * @param b 参数2
	 * @return void
	 */
	void accept(A a, B b);
}
