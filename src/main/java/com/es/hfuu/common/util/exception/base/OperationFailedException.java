package com.es.hfuu.common.util.exception.base;

import com.es.hfuu.common.util.exception.annotation.Exceptional;
import com.es.hfuu.common.util.exception.contants.ExceptionCode;

/**
 * @className OperationFailedException
 * @description 操作失败异常（使用场景：数据库，外部服务调用等）
 * @author lsx
 * @date 2019/11/8
 */
@Exceptional(isLogging = true, errorCode = ExceptionCode.OPERATION_FAILED)
public class OperationFailedException extends ParameterizableException {
	private static final long serialVersionUID = -5742607842249237190L;

	public OperationFailedException(String message, Throwable cause, Object... parameters) {
		super(message, "操作异常", cause, parameters);
	}

}
