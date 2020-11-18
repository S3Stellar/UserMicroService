package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadFormatEmailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2931035493302549190L;

	public BadFormatEmailException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BadFormatEmailException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
		
}
