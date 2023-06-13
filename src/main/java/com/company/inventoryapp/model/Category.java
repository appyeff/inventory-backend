/**
 * 
 */
package com.company.inventoryapp.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author yefer
 *
 */
@Entity
@Table(name="category")
@Data
public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1280070885115211044L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;

}
