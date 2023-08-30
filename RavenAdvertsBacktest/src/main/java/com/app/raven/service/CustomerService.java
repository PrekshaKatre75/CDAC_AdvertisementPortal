package com.app.raven.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.raven.dto.AuthoReqDto;
import com.app.raven.dto.CustomerDTO;
import com.app.raven.dto.LoginRespDTO;
import com.app.raven.model.Customer;
import com.app.raven.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService implements CustomerServicable {

	@Autowired
	private CustomerRepository cusRepo;
	@Autowired
	private ModelMapper mapp;

	/*
	 * This is a contructor providing @AutoWired By Default
	 */
	public CustomerService(CustomerRepository cusRepo) {
		this.cusRepo = cusRepo;
	}

	/*
	 * This method will allow the customer to login
	 */
	@Override
	public Customer findByEmailAndPassword(String email, String password) {
		for (Customer c : this.cusRepo.findAll()) { // We fetch all the customer and compare there email and password
													// wirh the customers in the database
			if (c.getEmail().equals(email) && c.getPassword().equals(password)) {
				System.out.println("Service " + c);
				return c; // Customer who match the requirements are returned
			}
		}

		return null;
	}

	/*
	 * This a registration method
	 */
	@Override
	public Customer saveCustomer(Customer custNew) { // this will save the user in the database
		System.out.println("Customer " + custNew + "Saved in the Repository");
		cusRepo.save(custNew);
		return custNew;
	}

	/*
	 * This is the method to update the user's credentials along with the password
	 * admin and the custome both will have this authority
	 */
	@Override
	public Customer updateCreds(Customer updateCust) {
		Customer updCus = this.cusRepo.findById(updateCust.getId()).orElse(null);
		updCus.setCompDet(updateCust.getCompDet());
		updCus.setAddress(updateCust.getAddress());
		updCus.setEmail(updateCust.getEmail());
		updCus.setMobileNo(updateCust.getMobileNo());
		updCus.setPassword(updateCust.getPassword());
		this.cusRepo.save(updCus);
		return updCus;
	}

	/*
	 * admin can check the user's list
	 */
	@Override
	public List<Customer> findAllCustomer() {
		System.out.println("List of all the customer is forwarded to the client");
		return cusRepo.findAll();
	}

	/*
	 * the selected customer is mapped by the id
	 */
	@Override
	public Customer findCustByID(Long id) {
		return cusRepo.findById(id).orElse(null);
	}

	@Override
	public String registerCustomer(CustomerDTO customer) {
		Customer custNew=mapp.map(customer, Customer.class);
		cusRepo.save(custNew);
		return "Added Successfully";
	}

	@Override
	public LoginRespDTO authenticateCustomer(@Valid AuthoReqDto request) {
		Customer Cust = cusRepo.findByEmailAndPassword(request.getEmail(), request.getPassword());
		// emp : persistent Entity ---> DTO
		LoginRespDTO authRespDTO = mapp.map(Cust, LoginRespDTO.class);
		return authRespDTO;
	}

}
