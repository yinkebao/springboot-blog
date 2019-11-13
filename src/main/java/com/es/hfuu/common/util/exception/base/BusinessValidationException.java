package com.es.hfuu.common.util.exception.base;

import com.es.hfuu.common.util.exception.annotation.Exceptional;
import com.es.hfuu.common.util.exception.base.handler.OperationExceptionHandler;
import com.es.hfuu.common.util.exception.contants.ExceptionCode;

/**
 * @className BusinessValidationException
 * @description 业务校验异常（使用场景：校验service返回值是否正常，校验实体类是否满足业务要求）
 * @author ykb
 * @date 2019/11/8
 */
@Exceptional(errorCode = ExceptionCode.BUSINESS_VALIDATION, handler = OperationExceptionHandler.class)
public class BusinessValidationException extends BaseAppRuntimeException {
	private static final long serialVersionUID = 4687039922500531976L;

	public BusinessValidationException() {
	}

	public BusinessValidationException(String message) {
		super(message, "业务异常");
	}
}