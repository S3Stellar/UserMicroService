package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.UsersManagementService;
import com.example.demo.boundary.UserBoundary;
import com.example.demo.dal.UserDao;
import com.example.demo.data.UserConverter;
import com.example.demo.data.UserEntity;
import com.example.demo.exceptions.BadFormatBirthdateException;
import com.example.demo.exceptions.BadFormatEmailException;
import com.example.demo.exceptions.BadFormatNameException;
import com.example.demo.exceptions.BadFormatPasswordException;
import com.example.demo.exceptions.BadFormatRoleException;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.validation.Validator;

@Service
public class UsersManagementServiceWithDB implements UsersManagementService{
	private UserDao userDao;
	private UserConverter userConverter;
	
	
	private Validator validator;
	
	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
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
		if(!this.validator.validateUserEmail(user.getEmail())) {
			throw new BadFormatEmailException("Email must be example@example.com");
		}
		
		if(!this.validator.validateUserPassword(user.getPassword())) {
			throw new BadFormatPasswordException("Password must be 5 letters long and containts at least one digit");
			
		}
		
		if(!this.validator.validateUserBirthdate(user.getBirthdate())) {
			throw new BadFormatBirthdateException("Birthdate must be in the format of dd-MM-yyyy");
		}
		
		if(!this.validator.validateUserName(user.getName())) {
			throw new BadFormatNameException("Name must not be empty or null");
		}
		
		if(!this.validator.validateUserRole(user.getRoles())) {
			throw new BadFormatRoleException("Role must not be empty or null");
		}
		
		Optional<UserEntity> userEntity = this.userDao.findById(user.getEmail());
		if(userEntity.isPresent())	{
			throw new UserAlreadyExistsException("User already exists in the system");
		}
		
		UserEntity userFromDB = this.userDao.save(this.userConverter.toEntity(user));
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
		throw new BadFormatPasswordException("incorrect password for email: "+email);
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

	@Override
	public UserBoundary[] searchByLastName(String value, int size, int page, String sortAttribute, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBoundary[] searchByMinimumAge(String value, int size, int page, String sortAttribute, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBoundary[] searchByRole(String value, int size, int page, String sortAttribute, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBoundary[] getAllUsers(int size, int page, String sortAttribute, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
