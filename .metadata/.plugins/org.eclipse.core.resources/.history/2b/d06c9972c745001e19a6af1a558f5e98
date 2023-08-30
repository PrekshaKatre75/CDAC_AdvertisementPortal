package com.app.raven.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.raven.model.Company;
import com.app.raven.model.Customer;
import com.app.raven.service.CompanyServicable;
import com.app.raven.service.CustomerServicable;
import com.app.raven.utils.Sorter;

@CrossOrigin("*")
@RestController
public class CompanyController {

	private CompanyServicable compSer; // Reference of the Company Service is created
	private CustomerServicable custSer; // Reference to the customer Service
	private Sorter sort;

	/*
	 * Constructor of the company controller is responsible for the dependency
	 * injection without the need of @Autowired annotation
	 */
	public CompanyController(CompanyServicable compSer, CustomerServicable custSer) {
		this.compSer = compSer;
		this.custSer = custSer;
		this.sort = new Sorter();
	}

	/*
	 * Method to save company in the database simulteniously assigning the cystomer
	 * the company
	 */
	@PostMapping("/newcompany/customer/{cust_id}")
	public Customer addCompanyCreds(@RequestBody Company comp, @PathVariable Long cust_id) {
		System.out.println(comp);
		comp.setLogo(sort.urlSorter(comp.getLogo())); // Fetch the url and modify it
		Customer cust = this.custSer.findCustByID(cust_id);
		cust.setCompDet(comp);
		this.compSer.saveCompany(comp);
		this.custSer.saveCustomer(cust);
		return cust;
	}

	/*
	 * Get the list of all the companies on call
	 */
	@GetMapping("/findallcompanies")
	public List<Company> getAllCompanies() {
		return this.compSer.allCompanies();
	}

	/*
	 * Method to fetch company from database using company id
	 */
	@GetMapping("/getcompany/{company_id}")
	public Company getCompanyById(@PathVariable Long company_id) {
		return this.compSer.findCompanyByID(company_id);
	}

	/*
	 * Method to update the company credentials of a customer
	 * Mark for testing
	 * call the setter method of customer to get the updated 
	 * customer
	 */
	@PostMapping("/editcompany")
	public Company editcredentials(@RequestBody Company editCom) {
		return this.compSer.updateCompanyCreds(editCom);
	}

}
