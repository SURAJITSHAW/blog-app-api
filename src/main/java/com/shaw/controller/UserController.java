package com.shaw.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shaw.exception.ResourceNotFound;
import com.shaw.payloads.ApiResponse;
import com.shaw.payloads.UserDto;
import com.shaw.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

//	@PostMapping("/")
//	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
//		UserDto userResDto = userServiceImpl.createUser(userDto);
//		return new ResponseEntity<>(userResDto, HttpStatus.CREATED);
//	}
	
	@PostMapping("/")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto, HttpServletRequest request) {
	    UserDto createdUserDto = userServiceImpl.createUser(userDto);
	    
	    // Construct the URI of the created resource
	    URI location = ServletUriComponentsBuilder
	                    .fromRequestUri(request)
	                    .path("/{id}")
	                    .buildAndExpand(createdUserDto.getId())
	                    .toUri();
	    
	    // Create a custom response object with the created user DTO and additional information
	    ApiResponse<UserDto> response = new ApiResponse<>(
	        HttpStatus.CREATED.value(),
	        "User created successfully",
	        createdUserDto,
	        location
	    );
	    
	    return ResponseEntity.created(location).body(response);
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
	public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable("id") Integer id) {
	    try {
	        userServiceImpl.deleteUser(id);
	        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully", null, null));
	    } catch (ResourceNotFound ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null, null));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to delete user", null, null));
	    }
	}

}
