package com.shaw.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Integer id;
	@NotEmpty
	private String name;
	@Email
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String about;
}
