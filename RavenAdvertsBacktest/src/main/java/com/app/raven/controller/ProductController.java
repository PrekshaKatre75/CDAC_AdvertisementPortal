package com.app.raven.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.raven.model.Customer;
import com.app.raven.model.Deliverables;
import com.app.raven.model.Product;
import com.app.raven.service.CustomerServicable;
import com.app.raven.service.DeliverablesServicable;
import com.app.raven.service.ProductServicable;
import com.app.raven.utils.PersistDeleverables;
import com.app.raven.utils.Sorter;

@CrossOrigin("*")
@RestController
@RequestMapping("/Products")
public class ProductController {
	private ProductServicable productService;
	private CustomerServicable custSer;
	private DeliverablesServicable delSer;
	private Sorter sort;

	public ProductController(ProductServicable productService, CustomerServicable custSer, DeliverablesServicable delSer) {
		this.productService = productService;
		this.custSer = custSer;
		this.delSer = delSer;
		this.sort = new Sorter();
	}

	/*
	 * fetch the customer and and assign the product to the list and save the
	 * product to the database
	 */
	@PostMapping("/addnewproduct/customer/{cust_id}")
	public Product addProduct(@RequestBody Product newProduct, @PathVariable Long cust_id) {
//		newProduct.setImage(this.sort.urlSorter(newProduct.getImage()));
		Customer cust = this.custSer.findCustByID(cust_id);
		cust.assignProd(newProduct);
		newProduct.setOwnerCust(cust);
		this.custSer.saveCustomer(cust);
		this.productService.saveProduct(newProduct);
		return newProduct;
	}

	/*
	 * Get the list of all the product in the customer's list
	 */
	@GetMapping("/allmyproduct")
	public List<Product> getProductList() {
		return this.productService.findAllProducts();
	}

	/*
	 * Delete the product from the customer's list
	 */
	@PostMapping("/delete/product/{prod_id}/customer/{cust_id}")
	public Product deleteProductFromList(@PathVariable Long prod_id, @PathVariable Long cust_id) {
		// remove from customer's list
		this.custSer.findCustByID(cust_id).removeProd(this.productService.findProductByID(prod_id));
		/*
		 * OR Customer cust = this.custSer.findCustByID(cust_id); Product pro =
		 * this.productService.findProductByID(prod_id); cust.removeProd(pro);
		 */
		return this.productService.deleteProduct(prod_id);
	}

	// Method to find product by Id
	@GetMapping("/getproduct/{product_id}")
	public Product getProductById(@PathVariable Long product_id) {
		return this.productService.findProductByID(product_id);
	}

	@PostMapping("/updateproudct")
	public Product updateProduct(@RequestBody Product product) {
		System.out.println("in update product " + product.getId() + " product : " + product);
		return this.productService.updateProduct(product);
	}

	/*
	 * method to fetch the list of the customer using the customer id
	 */
	@GetMapping("/getproduct/customer/{customer_id}")
	public List<Product>  getProductByCustomerId(@PathVariable Long customer_id){
		return this.custSer.findCustByID(customer_id).getProd();
	}
	
	/*
	 * Add Deliverables to the product using product id and the return the saved product;
	 */
	@PostMapping("/adddeliverables/product/{product_id}")
	public Product addDeliverables(@PathVariable Long product_id, @RequestBody Deliverables newDelivery) {
		newDelivery.setBannerImage(sort.urlSorter(newDelivery.getBannerImage()));
		newDelivery.setPoster(sort.urlSorter(newDelivery.getPoster()));
		PersistDeleverables locationsetter = new PersistDeleverables();
		Deliverables del = this.delSer.saveDeliverables(locationsetter.addLocationToDeleverables(newDelivery, this.productService.findProductByID(product_id).getLocation()));
		Product prod = this.productService.findProductByID(product_id);
		prod.setProdDeliverables(del);
		this.productService.saveProduct(prod);
		return prod;
	}
	
	@PostMapping("/getDeleverables/{d_id}")
	public Deliverables getDeleverable(@PathVariable Long d_id) {
		return this.delSer.findDeliverablesById(d_id);
	}
}
