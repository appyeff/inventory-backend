package com.company.inventoryapp.services;

import org.springframework.http.ResponseEntity;

import com.company.inventoryapp.model.Product;
import com.company.inventoryapp.response.ProductResponseRest;

public interface IProductService {
	
	public ResponseEntity<ProductResponseRest> search();
	
	public ResponseEntity<ProductResponseRest> searchById(Long id);

	public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);
	
	public ResponseEntity<ProductResponseRest> update(Product product, Long id);
	
	public ResponseEntity<ProductResponseRest> deleteById(Long id);


}
