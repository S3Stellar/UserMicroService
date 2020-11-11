package com.example.demo.data;

import org.springframework.stereotype.Component;
import com.example.demo.boundary.UserBoundary;

@Component
public class UserConverter {
	public UserEntity toEntity(UserBoundary user) {
		return new UserEntity(user.getEmail(), user.getPassword(), user.getName(),user.getBirthdate() ,
				user.getRoles());
	}
	
	public UserBoundary fromEntity(UserEntity user) {
		return new UserBoundary(user.getEmail(), user.getPassword(), user.getName(),user.getBirthdate() ,
				user.getRoles());
	}

	
}
