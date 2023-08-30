package com.app.exception;

public class PackageDetailsDetachedOrTransientException extends Exception {
	/**
	 * Serial Version id
	 */
	private static final long serialVersionUID = 1L;

	public PackageDetailsDetachedOrTransientException(String message) { // Cunstructor that take message and pass it to
																		// super class
		super(message);
	}

	public PackageDetailsDetachedOrTransientException(Exception e) { // Cunstructoe that take the throable cause and
																		// pass it to the super class
		super(e);
	}

	public PackageDetailsDetachedOrTransientException(String message, Exception e) { // Constructor that take the
																						// throable scause and message
																						// and pass it to the super
																						// Class
		super(message, e);
	}
}
