package com.doume.max.exception;

public class UserExistException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserExistException(){
	}
	
	public UserExistException(String string) {
		super(string);
	}
}
