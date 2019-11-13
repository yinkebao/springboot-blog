package com.es.hfuu.common.util.exception.base.handler;

import com.es.hfuu.common.util.exception.domain.ExceptionResult;

/**
 * @className Exceptional
 * @description 默认异常返回结果处理
 * @author ykb
 * @date 2019/11/8
 **/
public class DefaultExceptionHandler implements ExceptionHandler {

	/**
	 * 根据具体异常对异常明细信息进行处理
	 * @Title: handleException
	 * @param e 异常
	 * @param result 异常明细
	 * @return void
	 */
	@Override
	public void handleException(Exception e, ExceptionResult result) {
		result.setMessage("后台异常：" + e.getMessage());
	}
}
