package com.app.raven.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.jwtutility.JWTUtility;
import com.app.raven.dto.AuthoReqDto;
import com.app.raven.dto.CustomerDTO;
import com.app.raven.dto.PackageDTO;
import com.app.raven.model.Bill;
import com.app.raven.model.Customer;
import com.app.raven.model.PackageDetails;
import com.app.raven.model.Product;
import com.app.raven.service.BillServicable;
import com.app.raven.service.CustomerServicable;
import com.app.raven.service.PackageServicable;
import com.app.raven.service.ProductServicable;
import com.app.raven.utils.PersistBill;
import com.app.raven.utils.Sorter;
import com.app.security.UserDetailsServiceimpl;
import com.app.jwt.JwtRequest;
import com.app.jwt.JwtResponse;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin("*")
@RestController
public class CustomerController {
	// fields of repository
	@Autowired
	private CustomerServicable cusSer;
	@Autowired
	private PackageServicable packSer;
	@Autowired
	private ProductServicable prodSer;
	@Autowired
	private BillServicable billSer;
	@SuppressWarnings("unused")
	private Sorter sort;
	@Autowired
	private ModelMapper map;
	
	@Autowired(required = true)
	private  AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceimpl UserDetailsServiceimpl;
	
	@Autowired
	private JWTUtility jwtUtility;

	
    @PostMapping("/authenticate")
    @Operation(summary = "Authenticate user and get JWT Token")
    public com.app.jwt.JwtResponse authenticate(@RequestBody com.app.jwt.JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        UserDetails userDetails = null;
		try {
			userDetails = UserDetailsServiceimpl.loadUserByUsername(jwtRequest.getUsername());
		} catch (UsernameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        final String token =
                jwtUtility.generateToken(userDetails);
        return new com.app.jwt.JwtResponse(token);
    }
	
	
    
    
    
    
    
    
    
	/*
	 * Constructor providing @AutoWired
	 */
	public CustomerController(CustomerServicable cusSer, PackageServicable packSer, ProductServicable prodSer,
			BillServicable billSer) {
		this.cusSer = cusSer;
		this.packSer = packSer;
		this.prodSer = prodSer;
		this.billSer = billSer;
		this.sort = new Sorter();
	}

	/*
	 * Register a new Customer mapped with the front end using @RequestBody
	 */
	/*
	 * @PostMapping("/addCustomer") public Customer addCustomer(@RequestBody
	 * Customer cust) {
	 * 
	 * this.cusSer.saveCustomer(cust); return cust; }
	 */
	
	
	@PostMapping("/registerCustomer")
	public ResponseEntity<?> registerCustomer(@RequestBody CustomerDTO customer)
	{
		System.out.println("In Registereing Customer");
		
		return ResponseEntity.status(HttpStatus.OK).body(cusSer.registerCustomer(customer));
		
	}

	/*
	 * Customer purchas a package and the package is assigned to the customer the id
	 * for the custome and the package is fetched from the url comming
	 * in, @PathVariable is used to fetch the method.
	 */
	@PostMapping("/purchase/customer/{customer_id}")
	public Bill purchasePackage(@PathVariable Long customer_id, @RequestBody PackageDTO packDTO) {
		// Fetch Customer
		Customer cust = cusSer.findCustByID(customer_id);
		// Fetch Package
		PackageDetails pack=packSer.findPackageByID(customer_id);
		pack=map.map(packDTO,PackageDetails.class);
		pack.assignCust(cust);
		// set package
		cust.setPack(pack);
		// initialise the start and the end date
		cust.setPackageStartDate(LocalDate.now());
		cust.setPackageEndDate(cust.getPackageStartDate().plusDays(pack.getPValidity()));
		// initialise a bill object with the PersistBill class
		PersistBill bill = new PersistBill();
		// Create Bill object in Transient State
		Bill newOrderBill = bill.customerBill(cust);
		// Set the bill object in the Customers' associated field
		cust.setOrderDetails(newOrderBill);
		// Save the objects in the databases
		packSer.savePackage(pack);
		cusSer.saveCustomer(cust);
		billSer.saveBill(newOrderBill);
		return newOrderBill;
	}
	/*
	 * Customer can add products that they want to advertise and can be appended in
	 * a list
	 */
	@PostMapping("/getProduct/customer/{customer_id}/product/{product_id}")
	public Customer registerProduct(@PathVariable Long customer_id, @PathVariable Long product_id) {
		Customer cust = cusSer.findCustByID(customer_id);
		Product product = prodSer.findProductByID(product_id);
		cust.assignProd(product);
		product.setOwnerCust(cust);
		cusSer.saveCustomer(cust);
		prodSer.saveProduct(product);
		return cust;

	}

	/*
	 * This will Return the list of all the customers in the database for the admin
	 * to see and handle
	 */
	@GetMapping("/allCustomers")
	public List<Customer> seeAllCustomers() {
		return this.cusSer.findAllCustomer();
	}



	/*
	 * Method to fetch customer details using id
	 */
	@PostMapping("/getcustomer/{customer_id}")
	public Customer getCustomerById(@PathVariable Long customer_id) {
		return this.cusSer.findCustByID(customer_id);
	}

	/*
	 * The string objects are fetche in the method and used to login the
	 * customer, @RequestBody is used
	 */
	/*
	 * @PostMapping("/login") public Customer logigCustomer(@RequestBody AuthoReqDto
	 * User) { System.out.println(User);
	 * 
	 * Customer cust=cusSer.findByEmailAndPassword(User.getEmail(),
	 * User.getPassword());
	 * 
	 * 
	 * return cust; }
	 */
	@PostMapping("/login")
	public ResponseEntity<?> authenticateEmp(@RequestBody @Valid AuthoReqDto request) {
		System.out.println("in auth emp " + request);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(cusSer.authenticateCustomer(request));
		
	}

	/*
	 * The method Returns the bill object to generate a bill for the customer after
	 * payment is done
	 */
	@PostMapping("/getbill/{cust_id}")
	public Bill showBill(@PathVariable Long cust_id) {
		return this.cusSer.findCustByID(cust_id).getOrderDetails();
	}
}
