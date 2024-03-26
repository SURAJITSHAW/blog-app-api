package com.shaw.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private ModelMapper modelMapper;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;

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
        // Convert userDto to User entity
        User user = dtoToUser(userDto);

        // Encode password before persisting
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Save the user and get the persisted entity
        User savedUser = userRepo.save(user);

        // Convert the persisted User entity back to UserDto, excluding password
        return userToDto(savedUser);
    }

	private void updateUserDetails(User user, UserDto userDto) {
	    if (userDto.getName() != null) {
	        user.setName(userDto.getName());
	    }
	    if (userDto.getEmail() != null) {
	        user.setEmail(userDto.getEmail());
	    }
	    if (userDto.getAbout() != null) {
	        user.setAbout(userDto.getAbout());
	    }
	    if (userDto.getPassword() != null) {
	        user.setPassword(userDto.getPassword());
	    }
	}


	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		return userDto;
	}

}
