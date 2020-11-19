package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidNameException extends RuntimeException {

	private static final long serialVersionUID = 4048122430492015121L;

	public InvalidNameException() {
		super();
	}

	public InvalidNameException(String message) {
		super(message);
	}

	
	
}
