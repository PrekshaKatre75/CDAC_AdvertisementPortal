package com.app.exception;

public class ProductDetachedOrTransientException extends Exception {
	/**
	 * Serial Version id
	 */
	private static final long serialVersionUID = 1L;

	public ProductDetachedOrTransientException(String message) { // Cunstructor that take message and pass it to super
																	// class
		super(message);
	}

	public ProductDetachedOrTransientException(Exception e) { // Cunstructoe that take the throable cause and pass it to
																// the super class
		super(e);
	}

	public ProductDetachedOrTransientException(String message, Exception e) { // Constructor that take the throable
																				// cause and message and pass it to the
																				// super Class
		super(message, e);
	}
}
