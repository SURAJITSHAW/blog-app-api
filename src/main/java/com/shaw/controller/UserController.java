package com.shaw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaw.exception.ResourceNotFound;
import com.shaw.payloads.ApiResponse;
import com.shaw.payloads.UserDto;
import com.shaw.service.impl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto userResDto = userServiceImpl.createUser(userDto);
		return new ResponseEntity<>(userResDto, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") Integer id) {
		try {
			UserDto userDto = userServiceImpl.getUser(id);
			return new ResponseEntity<>(userDto, HttpStatus.OK);
		} catch (ResourceNotFound ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> usersDto = userServiceImpl.getAllUser();
		return new ResponseEntity<>(usersDto, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Integer id,@Valid @RequestBody UserDto userDto) {
		try {
			UserDto updatedUser = userServiceImpl.updateUser(userDto, id);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} catch (ResourceNotFound ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Integer id) throws ResourceNotFound {
		userServiceImpl.deleteUser(id);
		return new ResponseEntity<>(new ApiResponse(true, "User deleted successfully"), HttpStatus.OK);
//        try {
//            userServiceImpl.deleteUser(id);
//            return new ResponseEntity<>(new ApiResponse(true, "User deleted successfully"), HttpStatus.OK);
//        } catch (ResourceNotFound ex) {
//            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new ApiResponse(true, "Failed to delete user"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
	}
}
