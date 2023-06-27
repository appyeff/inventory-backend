package com.company.inventoryapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.inventoryapp.model.Product;

public interface IProductDao extends CrudRepository<Product, Long>{

}
