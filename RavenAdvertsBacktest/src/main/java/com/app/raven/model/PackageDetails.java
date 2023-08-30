package com.app.raven.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
@Data // Attaching Lombok library, Generates Getter , Setter , Parameterless Constructor , toString() , equals , hashCode
@EqualsAndHashCode(callSuper=false)
@Entity // Annotation specifies that the Class is an Entity (i.e. Creates Table in Database)
@Table(name = "PACKAGE")
@NoArgsConstructor 
@JsonIgnoreType
public class PackageDetails extends BaseEntity
{
	// Id PName Cost Validity
	@Column(name = "PACKAGE_NAME" , length = 25) // length = 25 -> Specifies the varchar length in Database Table
	private String PName;						 // name = "PACKAGE_NAME" -> Specifies Column Name in Database Table
	
	@Column(name = "PACKAGE_COST") // name = "PACKAGE_COST" -> Specifies Column Name in Database Table
	private double PCost;
	
	@Column(name = "PACKAGE_VALIDITY") // name = "PACKAGE_VALIDITY" -> Specifies Column Name in Database Table
	private int PValidity;
	
	
	@Column(name="package_logo")
	private String pLogo; // We will store the banner of the image here
	
	
//	@JsonBackReference
	@OneToMany(mappedBy = "pack", orphanRemoval = true)
	@ToString.Exclude // This will Exclide the toString method 
	private List<Customer> allSubscribers = new ArrayList<Customer>();
	
	public PackageDetails assignCust(Customer cust) {
		this.allSubscribers.add(cust);
		return this;
	}
}