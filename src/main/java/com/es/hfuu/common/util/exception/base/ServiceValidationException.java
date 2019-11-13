package com.es.hfuu.common.util.exception.base;

import com.es.hfuu.common.util.exception.annotation.Exceptional;
import com.es.hfuu.common.util.exception.contants.ExceptionCode;

/**
 * @className ServiceValidationException
 * @description 服务校验异常（使用场景：service调用失败）
 * @author ykb
 * @date 2019/11/8
 */
@Exceptional(isLogging = true, errorCode = ExceptionCode.SERVICE_VALIDATION)
public class ServiceValidationException extends ParameterizableException {
	private static final long serialVersionUID = 563323328675506887L;

	public ServiceValidationException(String message, Throwable cause) {
		super(message, "服务异常", cause);
	}

	public ServiceValidationException(String message, Throwable cause, Object... parameters) {
		super(message, "操作异常", cause, parameters);
	}
}