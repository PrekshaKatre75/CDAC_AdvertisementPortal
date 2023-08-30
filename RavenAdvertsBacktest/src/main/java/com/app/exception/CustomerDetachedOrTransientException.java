package com.app.exception;

public class CustomerDetachedOrTransientException extends Exception {
	
	/**
	 * Serial Version id 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerDetachedOrTransientException(String message) { // Cunstructor that throws exception and take message
		super(message);
	}
	
	public CustomerDetachedOrTransientException(Exception e) {
		super(e);
	}
	
	public CustomerDetachedOrTransientException(String message, Exception e) {
		super(message, e);
	}
}
