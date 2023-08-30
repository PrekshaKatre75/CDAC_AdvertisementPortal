package com.app.raven.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.raven.model.Company;
import com.app.raven.repository.CompanyRepositroy;

@Service
@Transactional
public class CompanyService implements CompanyServicable {
	
	@Autowired
	CompanyRepositroy compRepo; // reference of the company repository

	// Contructor helps in the dependency injection instead of using @AutoWired
	public CompanyService(CompanyRepositroy compRepo) {
		this.compRepo = compRepo;
	}

	@Override
	public Company saveCompany(Company comp) {
		System.out.println("Company Details to be Saved - " + comp);
		Company savedCompany = compRepo.save(comp); // Using the repository object the company is saved in the database
		return savedCompany;
	}

	/*
	 * Using the repository the object is mapped to a reference and then the creds
	 * are sequentially updated and saved
	 */
	@Override
	public Company updateCompanyCreds(Company updateCCreds) {
		Company companyToBeUpdated = findCompanyByID((long) updateCCreds.getId());

		System.out.println("Company Details to be Updated - " + updateCCreds);
		companyToBeUpdated.setCompanyName(updateCCreds.getCompanyName());
		companyToBeUpdated.setLinkdinPage(updateCCreds.getLinkdinPage());
		companyToBeUpdated.setLogo(updateCCreds.getLogo());
		companyToBeUpdated.setTwitter(updateCCreds.getTwitter());
		companyToBeUpdated.setWebsite(updateCCreds.getWebsite());
		companyToBeUpdated.setYoutubechannel(updateCCreds.getYoutubechannel());

		System.out.println("Updated Company Details - " + companyToBeUpdated);
		compRepo.save(companyToBeUpdated);

		return companyToBeUpdated;
	}

	/*
	 * List of all the companies is fetched in the method
	 * */
	@Override
	public List<Company> allCompanies() {
		List<Company> listOfCompanies = compRepo.findAll();
		return listOfCompanies;
	}

	/*
	 * Company is fetched by the id
	 * */
	@Override
	public Company findCompanyByID(Long id) {
		Company comp = compRepo.findById(id).orElse(null); // Optional<Company> - Optional Type is the returned by
															// "findById(id)" Method
		return comp;
	}
}