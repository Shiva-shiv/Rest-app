package com.wipro.exception;

public class UserException extends java.lang.Exception {

	public UserException() {

	}
	public UserException(String message) {
		super(message);
	}
	public UserException(String message,Throwable t) {
		super(message,t);
	}
}
