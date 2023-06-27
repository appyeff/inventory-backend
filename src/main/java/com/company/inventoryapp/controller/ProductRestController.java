/**
 * 
 */
package com.company.inventoryapp.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.inventoryapp.model.Product;
import com.company.inventoryapp.response.ProductResponseRest;
import com.company.inventoryapp.services.IProductService;
import com.company.inventoryapp.util.Util;

/**
 * @author yefer
 *
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
	
	private IProductService productService;
	
	public ProductRestController(IProductService productService) {
		super();
		this.productService = productService;
	}

	/**
	 * get all the products
	 * @return
	 */
	@GetMapping("/products")
	public ResponseEntity<ProductResponseRest> searchCategories(){
		
		return productService.search();
		
	}
	
	/**
	 * get products by id
	 * @param id
	 * @return
	 */
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> searchCategoriesById(@PathVariable Long id){
		
		return productService.searchById(id);
		
	}
	
	/**
	 * save products
	 * @param Product
	 * @return
	 */
	@PostMapping("/products")
	public ResponseEntity<ProductResponseRest> save(
			@RequestParam("picture") MultipartFile picture,
			@RequestParam("name") String name,
			@RequestParam("price") int price,
			@RequestParam("account") int account,
			@RequestParam("categoryId") Long categoryId)throws IOException{
		
		Product product = new Product();
		product.setName(name);
		product.setAccount(account);
		product.setPrice(price);
		product.setPicture(Util.compressZLib(picture.getBytes()));
		
		ResponseEntity<ProductResponseRest> response = productService.save(product, categoryId);
		return response;
		
	}
	
	/**
	 * update products
	 * @param Product
	 * @param id
	 * @return
	 */
	@PutMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> update(@RequestBody Product product, @PathVariable Long id){
		
		return productService.update(product, id);
	}
	
	/**
	 * update products
	 * @param id
	 * @return
	 */
	@DeleteMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> delete(@PathVariable Long id){
		
		return productService.deleteById(id);
	}
}
