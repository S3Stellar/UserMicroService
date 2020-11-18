package com.example.demo.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.example.demo.boundary.UserBoundary;
import com.example.demo.exceptions.BirthdateParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserConverter {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	public UserEntity toEntity(UserBoundary user) {
		
		try {
			return new UserEntity(user.getEmail(), user.getPassword(),
					user.getName(), dateFormat.parse(user.getBirthdate()) ,
					user.getRoles());
		} catch (RuntimeException | ParseException e) {
			throw new BirthdateParseException("Incorrect birthdate format");
		}
	}
	
	public UserBoundary fromEntity(UserEntity user) {
		return new UserBoundary(user.getEmail(), user.getPassword(),
				user.getName(), dateFormat.format(user.getBirthdate()) ,
				user.getRoles());
	}
	
}
