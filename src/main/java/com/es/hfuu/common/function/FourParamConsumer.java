package com.es.hfuu.common.function;

/**
 * @ClassName: FourParamConsumer
 * @Description: 四参数消费函数接口
 * @author: ykb
 * @date: 2018/8/29
 */
@FunctionalInterface
public interface FourParamConsumer<A, B, C, D> {

	/**
	 * 执行四参数消费型接口
	 * @Title: accept
	 * @param a 参数1
	 * @param b 参数2
	 * @param c 参数3
	 * @param d 参数4
	 * @return void
	 */
	void accept(A a, B b, C c, D d);
}
