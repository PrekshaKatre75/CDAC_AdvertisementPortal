package com.app.raven.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//Pojo for Billing details
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "bill_details")
@NoArgsConstructor 
@JsonIgnoreType
public class Bill extends BaseEntity {
	@Column(name = "company_name", length = 45)
	private String companyName;
	@Column(name = "total_cost")
	private double totalCost;
	@Column(name = "paymrnt_date")
	private LocalDate paymentDate;
}
