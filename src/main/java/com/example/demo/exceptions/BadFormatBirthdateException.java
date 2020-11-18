package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadFormatBirthdateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5535525574900731678L;

	public BadFormatBirthdateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BadFormatBirthdateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
}
