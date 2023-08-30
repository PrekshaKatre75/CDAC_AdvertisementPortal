package com.app.raven.dto;

import java.time.LocalDate;



import com.app.raven.model.PackageDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRespDTO {	
	
	private String email; 

	private String Fname;
	
	private String LName;

	private String mobileNo; 

	private String address; 
	
	private LocalDate packageStartDate; 

	private LocalDate packageEndDate;
	
	private PackageDetails pack;
}
