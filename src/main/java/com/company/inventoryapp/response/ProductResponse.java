package com.company.inventoryapp.response;

import java.util.List;

import com.company.inventoryapp.model.Product;

import lombok.Data;

@Data
public class ProductResponse {
	
	private List<Product> product;

}
