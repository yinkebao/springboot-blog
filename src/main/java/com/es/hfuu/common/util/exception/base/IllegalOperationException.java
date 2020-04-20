package com.es.hfuu.common.util.exception.base;

import com.es.hfuu.common.util.exception.annotation.Exceptional;
import com.es.hfuu.common.util.exception.contants.ExceptionCode;

/**
 * @className IllegalOperationException
 * @description 非法操作异常
 * @author lsx
 * @date 2019/11/8
 */
@Exceptional(isLogging = true, errorCode = ExceptionCode.ILLEGAL_OPERATION)
public class IllegalOperationException extends BaseAppRuntimeException {
	private static final long serialVersionUID = 7718041261027695981L;

	public IllegalOperationException(String message) {
		super(message, "操作异常");
	}

	public IllegalOperationException(String message, Throwable cause) {
		super(message, "操作异常", cause);
	}
}
