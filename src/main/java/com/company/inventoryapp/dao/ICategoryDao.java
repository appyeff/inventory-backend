package com.company.inventoryapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.inventoryapp.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long>{

}
