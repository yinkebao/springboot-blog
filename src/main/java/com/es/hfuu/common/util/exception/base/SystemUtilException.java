package com.es.hfuu.common.util.exception.base;

import com.es.hfuu.common.util.exception.annotation.Exceptional;
import com.es.hfuu.common.util.exception.contants.ExceptionCode;

/**
 * @className SystemUtilException
 * @description 系统工具异常
 * @author ykb
 * @date 2019/11/8
 */
@Exceptional(errorCode = ExceptionCode.SYSTEM_UTIL)
public class SystemUtilException extends ParameterizableException {
	private static final long serialVersionUID = -3529602622957764332L;

	public SystemUtilException(String message) {
		super(message, "工具异常");
	}

	public SystemUtilException(String message, Throwable cause) {
		super(message, "工具异常", cause);
	}

	public SystemUtilException(String message, Throwable cause, Object... parameters) {
		super(message, "操作异常", cause, parameters);
	}
}
