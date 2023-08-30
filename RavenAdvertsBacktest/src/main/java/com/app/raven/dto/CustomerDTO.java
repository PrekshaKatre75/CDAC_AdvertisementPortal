package com.app.raven.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	
	private String email; // Email field not null is a necessary fields and is used to login
	private String Fname;
	private String LName;
	private String password; // Password is a notnull fiels and is used to log in the user
	private String mobileNo; // Ex: +91-9525478562
	private String address; // Address is the address of the company which the customer belong to.


}
