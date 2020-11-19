package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BirthdateParseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BirthdateParseException(String message) {
		super(message);
	}

	public BirthdateParseException() {
	}

}
