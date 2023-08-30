package com.app.raven.service;

import java.util.List;

import javax.validation.Valid;

import com.app.raven.dto.AuthoReqDto;
import com.app.raven.dto.CustomerDTO;
import com.app.raven.dto.LoginRespDTO;
import com.app.raven.model.Customer;

public interface CustomerServicable {
	Customer findByEmailAndPassword(String email, String password); // Login the customer

	Customer saveCustomer(Customer custNew); // Registration 

	Customer updateCreds(Customer updateCust); // Update

	List<Customer> findAllCustomer(); // admin want to see all the customer

	Customer findCustByID(Long id); // admin want to see a perticular customer

	String registerCustomer(CustomerDTO customer);

	LoginRespDTO authenticateCustomer(@Valid AuthoReqDto request);
}
