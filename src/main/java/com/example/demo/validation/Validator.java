package com.example.demo.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Validator {

	
	public boolean validateUserEmail(String userEmail) {
		if (userEmail == null || userEmail.isEmpty()) {
			return false;
		}
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(userEmail);
		return matcher.matches();
	} 
	
	public boolean validateUserPassword(String password) {
		if (password == null || password.length() < 4) {
			return false;
		}
		return Pattern.compile("[0-9]+").matcher(password).find();
	}
	
}
