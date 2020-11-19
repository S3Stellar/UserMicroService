package com.example.demo.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.example.demo.boundary.Name;

@Component
public class Validator {
	private final static int DEFAULT_PASSWORD_LEN = 5;

	public boolean validateUserEmail(String userEmail) {
		if (userEmail == null || userEmail.isEmpty()) {
			return false;
		}
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
		
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(emailRegex,
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(userEmail);
		return matcher.matches();
	}

	public boolean validateUserPassword(String password) {
		if (password == null || password.length() < DEFAULT_PASSWORD_LEN) {
			return false;
		}
		return Pattern.compile("[0-9]+").matcher(password).find();
	}

	public boolean validateUserName(Name name) {
		return name != null && !name.getFirst().isEmpty() && !name.getLast().isEmpty();
	}

	public boolean validateUserBirthdate(String birthdate) {
		// TODO write more clean code
		String yearDigits = birthdate.substring(birthdate.length() - ((int)Math.log10(LocalDate.now().getYear()) + 1));
		int year;
		try {
			year = Integer.parseInt(yearDigits);
			if (year > LocalDate.now().getYear()) {
				throw new RuntimeException("Year is not valid");
			}
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			sdf.setLenient(false);
			sdf.parse(birthdate);

		} catch (NumberFormatException | ParseException e) {
			return false;
		}
		return true;
	}

	public boolean validateUserRole(String[] roles) {
		if (roles != null) {
			for (String role : roles)
				if (role == null || role.isEmpty())
					return false;
		}
		return true;
	}

}
