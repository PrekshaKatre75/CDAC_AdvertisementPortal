package com.app.raven.service;

import java.util.List;

import com.app.raven.model.Company;

public interface CompanyServicable {
	Company saveCompany(Company comp); // Save the company's creds

	Company updateCompanyCreds(Company updateCCreds); // Update the company creds

	List<Company> allCompanies(); // get the list of all the companies

	Company findCompanyByID(Long id); // get the creds of the compant by passing the id
}