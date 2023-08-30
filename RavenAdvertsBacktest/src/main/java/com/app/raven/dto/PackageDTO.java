package com.app.raven.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PackageDTO {

    private String PName;						
	
	private double PCost;

	private int PValidity;
	
	private String pLogo; 
}
