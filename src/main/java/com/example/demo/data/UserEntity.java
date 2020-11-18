package com.example.demo.data;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.boundary.Name;
import com.example.demo.validation.Email;

@Entity
@Table(name = "USERS")
public class UserEntity {
	
	private String email;	
	private String password;
	private Name name;
	private Date birthdate;
	private String[] roles;
	
	public UserEntity(String email, String password, Name name, Date birthdate, String[] roles) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.birthdate = birthdate;
		this.roles = roles;
	}
	
	public UserEntity() {
	}
	
	@Id
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

	@Embedded
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	//@Lob
	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
}
