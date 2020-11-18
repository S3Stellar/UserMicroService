package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class BadFormatPasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadFormatPasswordException(String message) {
		super(message);
	}
	
	public BadFormatPasswordException() {
	}
	
}
