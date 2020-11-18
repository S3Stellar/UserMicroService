package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class InvalidEmailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2931035493302549190L;

	public InvalidEmailException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidEmailException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
		
}
