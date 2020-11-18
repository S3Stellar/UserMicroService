package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.UsersManagementService;
import com.example.demo.boundary.UserBoundary;
import com.example.demo.dal.UserDao;
import com.example.demo.data.UserConverter;
import com.example.demo.data.UserEntity;

import com.example.demo.exceptions.InvalidBirthdateException;
import com.example.demo.exceptions.InvalidEmailException;
import com.example.demo.exceptions.InvalidNameException;
import com.example.demo.exceptions.InvalidPasswordException;
import com.example.demo.exceptions.InvalidRoleException;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.exceptions.BirthdateParseException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.validation.Validator;

@Service
public class UsersManagementServiceWithDB implements UsersManagementService {
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
	@Transactional
	public UserBoundary create(UserBoundary user) {
		if (!this.validator.validateUserEmail(user.getEmail())) {
			throw new InvalidEmailException("Email must be in the format of example@example.com");
		}

		if (!this.validator.validateUserPassword(user.getPassword())) {
			throw new InvalidPasswordException("Password must be 5 letters long and containts at least one digit");
		}

		if (!this.validator.validateUserBirthdate(user.getBirthdate())) {
			throw new InvalidBirthdateException("Birthdate must be in the format of dd-MM-yyyy");
		}

		if (!this.validator.validateUserName(user.getName())) {
			throw new InvalidNameException("Name must not be empty or null");
		}

		if (!this.validator.validateUserRole(user.getRoles())) {
			throw new InvalidRoleException("Role must not be empty or null");
		}

		Optional<UserEntity> userEntity = this.userDao.findById(user.getEmail());
		if (userEntity.isPresent()) {
			throw new UserAlreadyExistsException("User already exists in the system");
		}

		UserEntity userFromDB = this.userDao.save(this.userConverter.toEntity(user));
		return this.userConverter.fromEntity(userFromDB);
	}

	@Override
	@Transactional(readOnly = true)
	public UserBoundary getSpecificUser(String email) {
		if (!this.validator.validateUserEmail(email)) {
			throw new InvalidEmailException("Email must be example@example.com");
		}

		Optional<UserEntity> userFromDB = this.userDao.findById(email);
		if (userFromDB.isPresent())
			return this.userConverter.fromEntity(userFromDB.get());
		throw new UserNotFoundException("could not found user by email: " + email);
	}

	@Override
	@Transactional(readOnly = true)
	public UserBoundary login(String email, String password) {
		if (!this.validator.validateUserEmail(email)) {
			throw new InvalidEmailException("Email must be example@example.com");
		}
		if (!this.validator.validateUserPassword(password)) {
			throw new InvalidPasswordException("Password must be 5 letters long and containts at least one digit");
		}

		UserEntity userFromDB = this.userDao.findById(email)
				.orElseThrow(() -> new UserNotFoundException("The correspond user isn't found"));

		if (userFromDB.getPassword().equals(password))
			return this.userConverter.fromEntity(userFromDB);

		throw new InvalidPasswordException("incorrect password for email: " + email);
	}

	@Override
	@Transactional
	public void updateUser(String email, UserBoundary user) {
		UserBoundary existing = getSpecificUser(email);

		if (user != null) {

			if (this.validator.validateUserPassword(user.getPassword())) {
				existing.setPassword(user.getPassword());
			}

			if (this.validator.validateUserName(user.getName())) {
				existing.setName(user.getName());
			}

			if (this.validator.validateUserBirthdate(user.getBirthdate())) {
				existing.setBirthdate(user.getBirthdate());
			}

			if (this.validator.validateUserRole(user.getRoles())) {
				existing.setRoles(user.getRoles());
			}
		}
		
		this.userDao.save(this.userConverter.toEntity(existing));
	}

	@Override
	@Transactional
	public void deleteAllUsers() {
		this.userDao.deleteAll();
	}

	@Override
	@Transactional
	public List<UserBoundary> searchByLastName(String value, int size, int page, String sortAttribute,
			String sortOrder) {

		Direction direction;
		if (sortOrder.equals("ASC"))
			direction = Direction.ASC;
		else
			direction = Direction.DESC;

		return this.userDao.findAllByName_last(value, PageRequest.of(page, size, direction, sortAttribute)).stream()
				.map(this.userConverter::fromEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<UserBoundary> searchByMinimumAge(String value, int size, int page, String sortAttribute,
			String sortOrder) {

		Direction direction;
		if (sortOrder.equals("ASC"))
			direction = Direction.ASC;
		else
			direction = Direction.DESC;

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date parsedDate;
		try {
			parsedDate = dateFormat.parse(value);
		} catch (ParseException e) {
			throw new BirthdateParseException();
		}

		return this.userDao
				.findAllByBirthdateGreaterThanEqual(parsedDate, PageRequest.of(page, size, direction, sortAttribute))
				.stream().map(this.userConverter::fromEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<UserBoundary> searchByRole(String value, int size, int page, String sortAttribute, String sortOrder) {
		Direction direction;
		if (sortOrder.equals("ASC"))
			direction = Direction.ASC;
		else
			direction = Direction.DESC;

		return this.userDao.findAllByRoles(value, PageRequest.of(page, size, direction, sortAttribute)).stream()
				.map(this.userConverter::fromEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<UserBoundary> getAllUsers(int size, int page, String sortAttribute, String sortOrder) {
		Direction direction;
		if (sortOrder.equals("ASC"))
			direction = Direction.ASC;
		else
			direction = Direction.DESC;

		return this.userDao.findAll(PageRequest.of(page, size, direction, sortAttribute)).stream()
				.map(this.userConverter::fromEntity).collect(Collectors.toList());
	}

}
