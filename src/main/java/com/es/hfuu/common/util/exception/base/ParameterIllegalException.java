package com.es.hfuu.common.util.exception.base;

import com.es.hfuu.common.util.exception.annotation.Exceptional;
import com.es.hfuu.common.util.exception.base.handler.OperationExceptionHandler;
import com.es.hfuu.common.util.exception.contants.ExceptionCode;

/**
 * @className ParameterIllegalException
 * @description 参数校验失败异常（使用场景：校验参数是否合法）
 * @author ykb
 * @date 2019/11/8
 */
@Exceptional(errorCode = ExceptionCode.PARAMETER_ILLEGAL, handler = OperationExceptionHandler.class)
public class ParameterIllegalException extends BaseAppRuntimeException {
	private static final long serialVersionUID = -2227238147852122329L;

	public ParameterIllegalException(String message) {
		super(message, "参数异常");
	}

}
