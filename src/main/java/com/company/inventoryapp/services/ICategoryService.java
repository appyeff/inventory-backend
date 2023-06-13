package com.company.inventoryapp.services;

import org.springframework.http.ResponseEntity;

import com.company.inventoryapp.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();

}
