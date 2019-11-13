package com.es.hfuu.common.util.exception.base;

/**
 * @className BaseAppException
 * @description 基础受检查异常
 * @author ykb
 * @date 2019/11/8
 */
public class BaseAppException extends Exception {
	private static final long serialVersionUID = 8343048459443313229L;
	private String title;

	public BaseAppException() {
	}

	public BaseAppException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseAppException(String title, String message, Throwable cause) {
		super(message, cause);
		this.title = title;
	}

	public BaseAppException(String message) {
		super(message);
	}

	public BaseAppException(Throwable cause) {
		super(cause);
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}