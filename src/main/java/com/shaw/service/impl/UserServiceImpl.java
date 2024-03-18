package com.shaw.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaw.entity.User;
import com.shaw.exception.ResourceNotFound;
import com.shaw.payloads.UserDto;
import com.shaw.repository.UserRepo;
import com.shaw.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDto getUser(Integer id) throws ResourceNotFound {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User", "id", id));
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = userRepo.findAll();
		return users.stream().map(this::userToDto).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer id) throws ResourceNotFound {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User", "id", id));
		userRepo.delete(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) throws ResourceNotFound {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User", "id", id));
		updateUserDetails(user, userDto);
		User updatedUser = userRepo.save(user);
		return userToDto(updatedUser);
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = dtoToUser(userDto);
		User savedUser = userRepo.save(user);
		return userToDto(savedUser);
	}

	private void updateUserDetails(User user, UserDto userDto) {
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
	}

	private User dtoToUser(UserDto userDto) {
		User user = new User();
		updateUserDetails(user, userDto);
		return user;
	}

	private UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());
		return userDto;
	}

}
