package com.es.hfuu.common.function;

/**
 * @ClassName: FiveParamConsumer
 * @Description: 五参数消费函数接口
 * @author: ykb
 * @date: 2018/8/29
 */
@FunctionalInterface
public interface FiveParamConsumer<A, B, C, D, E> {

	/**
	 * 执行五参数消费型接口
	 * @Title: accept
	 * @param a 参数1
	 * @param b 参数2
	 * @param c 参数3
	 * @param d 参数4
	 * @param e 参数5
	 * @return void
	 */
	void accept(A a, B b, C c, D d, E e);
}
