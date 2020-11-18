package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code =HttpStatus.BAD_REQUEST)
public class BadFormatRoleException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1774577565485621395L;

	public BadFormatRoleException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BadFormatRoleException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
