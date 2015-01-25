package com.doume.max.exception;

public class PasswordIncorrectException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordIncorrectException(){
	}
	
	public PasswordIncorrectException(String string) {
		super(string);
	}
}
