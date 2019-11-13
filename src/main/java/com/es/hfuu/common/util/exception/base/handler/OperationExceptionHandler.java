package com.es.hfuu.common.util.exception.base.handler;

import com.es.hfuu.common.util.exception.domain.ExceptionResult;

/**
 * @className Exceptional
 * @description 操作异常返回结果处理
 * @author ykb
 * @date 2019/11/8
 */
public class OperationExceptionHandler implements ExceptionHandler {

	@Override
	public void handleException(Exception bex, ExceptionResult result) {
		result.setMessage(bex.getMessage());
	}
}
