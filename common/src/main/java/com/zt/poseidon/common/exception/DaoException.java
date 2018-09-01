package com.zt.poseidon.common.exception;

/**
 * dao 层通用 Exception
 */
public class DaoException extends RuntimeException {

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = 3583566093089790852L;
}