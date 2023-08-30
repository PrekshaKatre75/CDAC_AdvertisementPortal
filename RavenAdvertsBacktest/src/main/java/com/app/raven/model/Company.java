package com.app.raven.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

//import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Pojo for company details
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Table(name = "company_details")
@NoArgsConstructor 
@JsonIgnoreType
public class Company extends BaseEntity {

	@Column(name = "company_name", unique = true)
	private String companyName;
	@Column(name = "website")
	private String website;
	@Column(name = "company_logo")
	private String logo;
	@Column(name = "twitter")
	private String twitter; // to store address of company's twitter page
	@Column(name = "youtube")
	private String youtubechannel;// to store address of youtube channel
	@Column(name = "linkdin")
	private String linkdinPage; // to store address of company linkdin page
}
