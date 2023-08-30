package com.app.raven.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // This annotation tells the compiler that what follows is a part of the entity
@Data // The lombok lib. provide Getters, Setters, Parameterised, parameterless
		// constructor, hashcode equals and toString method
@EqualsAndHashCode(callSuper = false)
@Table(name = "product") // table annotation overriding the name of table to add a suitable name in
							// database
@NoArgsConstructor
@JsonIgnoreType
public class Product extends BaseEntity {
	@Column(name = "product_name", length = 25) // annotation used to specify the length of the varchar and to rename
												// the column
	private String productName; // Product name added
	@Column(name = "product_details") // Modify the type from varchar to Text
	private String productDetails; // Products' details are added in this field and is of the type text
	@Column(name = "Product_Image") // The Path of the images will be given here
	private String image; // stores the path of the images of the promotional material

	@Column(name = "ad_location")
	private String location; // Advertisement Location of the product

	@Column(name = "ad_scope")
	private String scope; // Scope of the project Local/ state/ pan India

	@ManyToOne
	@JoinColumn(name = "customer_id")
//	@JsonManagedReference
	@JsonIgnore
	@ToString.Exclude
	private Customer ownerCust;

	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL) // the deliverables with which the product is affileated is mapped 1 to 1 as 1
											// deliverables belong to 1 product
	@JoinColumn(name = "deliverables_id")
	private Deliverables prodDeliverables; // Details of the company are mapped here
}
