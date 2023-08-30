package com.app.raven.model;

import lombok.Data;

@Data
public class LoginObject { // object is used to login the user
	private String email; // the email is fetched and compared with the customer's email
	private String password; // the password is fetched and compared with the customer's password
}
