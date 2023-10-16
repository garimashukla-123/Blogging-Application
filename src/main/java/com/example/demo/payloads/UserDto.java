package com.example.demo.payloads;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor


public class UserDto {
// we use that field only which we have to take by user
	
@Getter @Setter
private int id;
	@NotEmpty
	@Size(min = 4, message = "Username should be of min 4 characters")
	private String name;
	@NotEmpty
	@Size(min = 3,max = 10 , message = "password must be minimum of 3 characters")
	private String password;
	@NotEmpty
	private String about;
	@Email(message = "Email address is invalid !!")
	 private String email;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	}
	


