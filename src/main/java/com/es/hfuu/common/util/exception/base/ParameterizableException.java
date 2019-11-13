package com.es.hfuu.common.util.exception.base;

/**
 * @className ParameterizableException
 * @description 参数化异常
 * @author ykb
 * @date 2019/11/8
 */
public class ParameterizableException extends BaseAppRuntimeException {

	/** 调用参数 */
	private Object[] parameters;

	public ParameterizableException(String message, String title) {
		super(message, title);
	}

	public ParameterizableException(String message, String title, Throwable cause, Object... parameters) {
		super(message, title, cause);
		this.parameters = parameters;
	}

	public Object[] getParameters() {
		return parameters;
	}
}
