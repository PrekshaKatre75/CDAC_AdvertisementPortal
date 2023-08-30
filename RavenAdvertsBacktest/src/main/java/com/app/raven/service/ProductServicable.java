package com.app.raven.service;

import java.util.List;

import com.app.raven.model.Product;

public interface ProductServicable {
	Product saveProduct(Product prod); // Save the product in the database

	List<Product> findAllProducts(); // Find the list of all the product

	Product findProductByID(Long id); // Get product by id

	Product updateProduct(Product updateProd); // get the updated product and save it to the database

	Product deleteProduct(Long id); // Delete product from the list

}
