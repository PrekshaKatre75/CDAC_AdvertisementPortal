package com.app.raven.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.raven.model.Customer;

//DAO of Customer
/*
* JpaRepository is used to manage data in spring boot application.
* It is used to perform basic CRUD o
* perations 
* This will enable Spring Data to find this interface and automatically create an implementation for it.
*/
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmailAndPassword(String email, String password);
	

}
