package com.es.hfuu.common.util.exception.base;

/**
 * @className BaseAppRuntimeException
 * @description 基础运行时异常
 * @author lsx
 * @date 2019/11/8
 */
public class BaseAppRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -6021077900819863433L;
	private String title;

	public BaseAppRuntimeException() {
	}

	public BaseAppRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseAppRuntimeException(String message, String title) {
		super(message);
		this.title = title;
	}

	public BaseAppRuntimeException(String message, String title, Throwable cause) {
		super(message, cause);
		this.title = title;
	}

	public BaseAppRuntimeException(String message) {
		super(message);
	}

	public BaseAppRuntimeException(Throwable cause) {
		super(cause);
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}