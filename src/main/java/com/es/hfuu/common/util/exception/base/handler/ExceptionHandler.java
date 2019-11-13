package com.es.hfuu.common.util.exception.base.handler;

import com.es.hfuu.common.util.exception.domain.ExceptionResult;

/**
 * @className Exceptional
 * @description 异常处理接口
 * @author ykb
 * @date 2019/11/8
 */
public interface ExceptionHandler {

	/**
	 * 根据具体异常对异常明细信息进行处理
	 * @Title: handleException
	 * @param e 异常
	 * @param result 异常明细
	 * @return void
	 */
	void handleException(Exception e, ExceptionResult result);
}
