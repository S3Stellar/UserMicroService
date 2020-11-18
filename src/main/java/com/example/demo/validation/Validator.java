package com.example.demo.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.example.demo.boundary.Name;

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
		if (password == null || password.length() < 5) {
			return false;
		}
		return Pattern.compile("[0-9]+").matcher(password).find();
	}

	public boolean validateUserName(Name name) {
		return name != null && !name.getFirst().isEmpty() && !name.getLast().isEmpty();
	}

	public boolean validateUserBirthdate(String birthdate) {
		// TODO start 
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		formatter.setLenient(false);
		try {
			formatter.parse(birthdate);

		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public boolean validateUserRole(String[] roles) {
	
		if (roles != null) {
			for (String role : roles)
				if (role == null || role.isEmpty()) {
					return false;
				}
		}
		return true;
	}

}
