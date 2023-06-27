package com.company.inventoryapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventoryapp.constants.Constantes;
import com.company.inventoryapp.dao.ICategoryDao;
import com.company.inventoryapp.dao.IProductDao;
import com.company.inventoryapp.model.Category;
import com.company.inventoryapp.model.Product;
import com.company.inventoryapp.response.ProductResponseRest;


@Service
public class ProductServiceImpl implements IProductService{
	
	private IProductDao productDao;
	private ICategoryDao categoryDao;

	public ProductServiceImpl(ICategoryDao categoryDao, IProductDao productDao) {
		super();
		this.categoryDao = categoryDao;
		this.productDao = productDao;
	}
	

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductResponseRest> search() {
		
		ProductResponseRest response = new ProductResponseRest();
		
		try {
			List<Product> product = (List<Product>) productDao.findAll();
			
			response.getProductResponse().setProduct(product);
			response.setMetadata(Constantes.METADATA_OK, "00", "Respuesta exitosa");
			
		} catch (Exception e) {
			response.setMetadata(Constantes.METADATA_NOK, "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductResponseRest> searchById(Long id) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		
		try {
			Optional<Product> product = productDao.findById(id);
			
			if(product.isPresent()) {
				list.add(product.get());
				response.getProductResponse().setProduct(list);
				response.setMetadata(Constantes.METADATA_OK, "00", "Categoria encontrada");
			} else {
				response.setMetadata(Constantes.METADATA_NOK, "-1", "Categoria no encontrada");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			response.setMetadata(Constantes.METADATA_NOK, "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		
		try {			
			// search category to set in the product object
			Optional<Category> category = categoryDao.findById(categoryId);
			
			if(category.isPresent()) {
				product.setCategory(category.get());
			} else {
				response.setMetadata(Constantes.METADATA_NOK, "-1", "Categoria no encontrada asociada");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			
			// save the product			
			Product productSaved = productDao.save(product);
			
			if(productSaved != null) {
				list.add(productSaved);
				response.getProductResponse().setProduct(list);
				response.setMetadata(Constantes.METADATA_OK, "00", "Producto guardado");
			} else {
				response.setMetadata(Constantes.METADATA_NOK, "-1", "Producto no guardado");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}			
		} catch (Exception e) {
			response.setMetadata(Constantes.METADATA_NOK, "-1", "Error al guardar producto");
			e.getStackTrace();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> update(Product product, Long id) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		
		try {
			Optional<Product> productSearch = productDao.findById(id);
			if (productSearch.isPresent()) {
				// se procedera a actualizar el registro
				productSearch.get().setName(product.getName());
				//productSearch.get().setDescription(product.getDescription());
				
				Product productToUpdate = productDao.save(productSearch.get());
				
				if(productToUpdate != null) {
					list.add(productToUpdate);
					response.getProductResponse().setProduct(list);
					response.setMetadata(Constantes.METADATA_OK, "00", "Categoria actualizada");
				} else {
					response.setMetadata(Constantes.METADATA_NOK, "-1", "Categoria no actualizada");
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				response.setMetadata(Constantes.METADATA_NOK, "-1", "Categoria no encontrada");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			response.setMetadata(Constantes.METADATA_NOK, "-1", "Error al actualizar categoria");
			e.getStackTrace();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> deleteById(Long id) {
		
		ProductResponseRest response = new ProductResponseRest();
		
		try {
			productDao.deleteById(id);
			response.setMetadata(Constantes.METADATA_OK, "00", "Categoria eliminada");
			
		} catch (Exception e) {
			response.setMetadata(Constantes.METADATA_NOK, "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
