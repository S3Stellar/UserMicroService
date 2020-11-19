package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =HttpStatus.BAD_REQUEST)
public class InvalidRoleException extends RuntimeException {

	private static final long serialVersionUID = -1774577565485621395L;

	public InvalidRoleException() {
		super();
	}

	public InvalidRoleException(String message) {
		super(message);
	}

	
}
