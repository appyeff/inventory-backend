package com.company.inventoryapp.response;

import java.util.List;

import com.company.inventoryapp.model.Category;

import lombok.Data;

@Data
public class CategoryResponse {
	
	private List<Category> category;

}
