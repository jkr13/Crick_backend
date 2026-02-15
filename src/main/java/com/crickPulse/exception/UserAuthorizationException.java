package com.crickPulse.exception;

/**
 * 
 * @author jasani-chirag
 *
 */
public class UserAuthorizationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String error;

	public UserAuthorizationException(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
