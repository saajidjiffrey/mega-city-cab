package com.mega_city_cab.exception;

public class AuthenticationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause); // Properly call Exception constructor to store the cause
	}
}
