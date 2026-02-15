package com.crickPulse.exception;

/**
 * This exception class use to throw all type of User exception
 * 
 * @author jasani-chirag
 *
 */
public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	String error;

	public UserException(String error) {

		this.error = error;

	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
