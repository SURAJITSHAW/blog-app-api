package com.shaw.service;

import java.util.List;

import com.shaw.exception.ResourceNotFound;
import com.shaw.payloads.UserDto;

public interface UserService {
	// get a user
	UserDto getUser(Integer id) throws ResourceNotFound;

	// get all user
	List<UserDto> getAllUser();

	// delete user
	void deleteUser(Integer id) throws ResourceNotFound;

	// update user
	UserDto updateUser(UserDto user ,Integer id) throws ResourceNotFound;

	// create user
	UserDto createUser(UserDto user);
}
