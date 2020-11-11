package com.example.demo.boundary;

public class UserBoundary {
	private String email;
	private String password;
	private Name name;
	private String birthdate;
	private String[] roles;
	
	public UserBoundary(String email, String password, Name name, String birthdate, String[] roles) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.birthdate = birthdate;
		this.roles = roles;
	}
	
	public UserBoundary() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
}
