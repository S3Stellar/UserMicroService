package com.example.demo;

import com.example.demo.boundary.UserBoundary;

public interface UsersManagementService {

	public UserBoundary create(UserBoundary user);

	public UserBoundary getSpecificUser(String email);

	public UserBoundary login(String email, String password);

	public void updateUser(String email, UserBoundary user);

	public void deleteAllUsers();
}
