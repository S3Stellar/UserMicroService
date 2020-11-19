package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.boundary.UserBoundary;
import com.example.demo.service.UsersManagementService;
import com.example.demo.utility.*;

@RestController
public class UsersManagementController {
	private UsersManagementService usersManagementService;

	@Autowired
	public void setUsersManagementService(UsersManagementService usersManagementService) {
		this.usersManagementService = usersManagementService;
	}
	
	@RequestMapping(
			path = "/users",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary create(@RequestBody UserBoundary user) {
		return this.usersManagementService.create(user);
	}
	
	@RequestMapping(
			path = "/users/{email}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary getSpecificUser(@PathVariable("email") String email) {
		return this.usersManagementService.getSpecificUser(email);
	}
	
	
	@RequestMapping(
			path = "/users/login/{email}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary login(@PathVariable("email") String email, @RequestParam(name = "password", required = false)String password) {
		return this.usersManagementService.login(email,password);
	}
	
	@RequestMapping(
			path = "/users/{email}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(@RequestBody UserBoundary user, @PathVariable("email") String email) {
		this.usersManagementService.updateUser(email, user);
	}
	
	@RequestMapping(
			path = "/users",
			method = RequestMethod.DELETE)
	public void updateUser() {
		this.usersManagementService.deleteAllUsers();
	}
	
	@RequestMapping(
			path = "/users/search",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] searchUsers (
			@RequestParam(name = "criteriaType", required = false, defaultValue = "") String type,
			@RequestParam(name = "criteriaValue", required = false) String value,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "sortBy", required = false, defaultValue = "email") String sortAttribute,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder){
		
		switch (type) {
		case ControllerTypes.BY_LAST_NAME:
			return this.usersManagementService.searchByLastName(value, size, page, sortAttribute, sortOrder).toArray(new UserBoundary[0]);
		case ControllerTypes.BY_MINIMUM_AGE:
			return this.usersManagementService.searchByMinimumAge(value, size, page, sortAttribute, sortOrder).toArray(new UserBoundary[0]);
		case ControllerTypes.BY_ROLE:
			return this.usersManagementService.searchByRole(value, size, page, sortAttribute, sortOrder).toArray(new UserBoundary[0]);
		default:
			return this.usersManagementService.getAllUsers(size, page, sortAttribute, sortOrder).toArray(new UserBoundary[0]);

		}	
	}

}
