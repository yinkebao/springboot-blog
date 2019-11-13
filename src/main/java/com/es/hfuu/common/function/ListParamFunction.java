package com.es.hfuu.common.function;

/**
 * @ClassName: ListParamFunction
 * @Description: 列表参数函数
 * @author: ykb
 * @date: 2018/8/29
 */
@FunctionalInterface
public interface ListParamFunction<A, R> {

	/**
	 * 执行分页功能型接口
	 * @Title: apply
	 * @param a 参数1
	 * @param page    当前页码
	 * @param rows	单页显示数量
	 * @param sidx	排序字段
	 * @param sord	升序或者降序
	 * @return R
	 */
	R apply(A a, Integer page, Integer rows, String sidx, String sord);
}
