package com.app.raven.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AuthoReqDto {
	@NotBlank(message="Email can't be blank")
	@Email(message="Inavlid Email format")
	private String email;
	@NotBlank(message="Password can't be blank")
	
	private String password;


}
