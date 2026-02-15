package com.crickPulse.exception;

/**
 * This class use to throw invalid data exception
 * 
 * @author jasani-chirag
 *
 */
public class PaymentDetailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String error;

	public PaymentDetailException(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
