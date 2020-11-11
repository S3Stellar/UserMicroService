package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.UsersManagementService;
import com.example.demo.boundary.UserBoundary;
import com.example.demo.dal.UserDao;
import com.example.demo.data.UserConverter;
import com.example.demo.data.UserEntity;
import com.example.demo.exceptions.IncorrectPasswordException;
import com.example.demo.exceptions.UserNotFoundException;

@Service
public class UsersManagementServiceWithDB implements UsersManagementService{
	private UserDao userDao;
	private UserConverter userConverter;
	
	@Autowired
	public void setUserConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserBoundary create(UserBoundary user) {
		UserEntity userFromDB= this.userDao.save(this.userConverter.toEntity(user));
		return this.userConverter.fromEntity(userFromDB);
	}

	@Override
	public UserBoundary getSpecificUser(String email) {
		Optional<UserEntity> userFromDB = this.userDao.findById(email);
		if(userFromDB.isPresent())
			return this.userConverter.fromEntity(userFromDB.get());
		throw new UserNotFoundException("could not found user by email: "+email);
		}

	@Override
	public UserBoundary login(String email, String password){
		UserBoundary userFromDB = getSpecificUser(email);
		if(userFromDB.getPassword().equals(password))
			return userFromDB;
		throw new IncorrectPasswordException("incorrect password for email: "+email);
	}

	@Override
	public void updateUser(String email, UserBoundary user) {
		UserBoundary userFromDB = getSpecificUser(email);
		//TODO complete logic
		//update the user from the data-base and save the new user
		this.userDao.save(this.userConverter.toEntity(userFromDB));
	}

	@Override
	public void deleteAllUsers() {
		this.userDao.deleteAll();
	}
	
}
