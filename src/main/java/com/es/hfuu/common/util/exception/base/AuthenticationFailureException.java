package com.es.hfuu.common.util.exception.base;


import com.es.hfuu.common.util.exception.annotation.Exceptional;
import com.es.hfuu.common.util.exception.contants.ExceptionCode;

/**
 * @className AuthenticationFailureException
 * @description 权限异常类
 * @author lsx
 * @date 2019/11/8
 */
@Exceptional(errorCode = ExceptionCode.AUTHENTICATION_FAILURE)
public class AuthenticationFailureException extends BaseAppRuntimeException {
	private static final long serialVersionUID = 3314345147616765907L;

	public AuthenticationFailureException(String message) {
		super(message, "认证异常");
	}

	public AuthenticationFailureException(String message, Throwable cause) {
		super(message, "认证异常", cause);
	}
}
