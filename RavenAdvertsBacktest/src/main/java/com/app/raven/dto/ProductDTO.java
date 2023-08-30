package com.app.raven.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	private String productName;
	
	private String productDetails; 
	
	private String image; 

	private String location; 
	
	private String scope;

}
