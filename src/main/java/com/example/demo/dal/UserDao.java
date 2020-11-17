package com.example.demo.dal;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.data.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, String> {

	public List<UserEntity> findAllByName_last(
			@Param("last") String last,
			Pageable pageable);
	
	public List<UserEntity> findAllByBirthdateGreaterThanEqual (
			@Param("birthdate") Date birthdate,
			Pageable pageable);
	
	public List<UserEntity> findAllByRoles(
			@Param("roles") String roles,
			Pageable pageable);
	
	
	/*
	 * public List<UserBoundary> findAllUsers(
	 * 
	 * @Param("size") int size,
	 * 
	 * @Param("page") int page,
	 * 
	 * @Param("sortAttribute") String sortAttribute,
	 * 
	 * @Param("sortOrder") String sortOrder, Pageable pageable);
	 */
	 

}
