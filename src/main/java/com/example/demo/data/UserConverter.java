package com.example.demo.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.example.demo.boundary.UserBoundary;
import com.example.demo.exceptions.BirthdateParseException;

@Component
public class UserConverter {
	private SimpleDateFormat dateFormat;

	@PostConstruct
	public void setup() {
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	}

	public UserEntity toEntity(UserBoundary user) {

		try {
			return new UserEntity(
					user.getEmail(), 
					user.getPassword(), 
					new Name(user.getName().getFirst(), user.getName().getLast()),
					dateFormat.parse(user.getBirthdate()), 
					convertToRolesEntity(user.getRoles()));
		} catch (RuntimeException | ParseException e) {
			throw new BirthdateParseException("Incorrect birthdate format");
		}
	}

	public UserBoundary fromEntity(UserEntity user) {
		return new UserBoundary(
				user.getEmail(),
				user.getPassword(),
				new com.example.demo.boundary.Name(user.getName().getFirst(), user.getName().getLast()) ,
				dateFormat.format(user.getBirthdate()), 
				convertFromRolesEntity(user.getRoles()));
	}

	public List<String> convertToRolesEntity(String[] roles) {
		List<String> list = new ArrayList<>();
		for (String role : roles)
			list.add(role);
		return list;
	}

	public String[] convertFromRolesEntity(List<String> roles) {
		return roles.toArray(new String[roles.size()]);
	}

}
