/**
 * 
 */
package com.company.inventoryapp.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author yefer
 *
 */
@Entity
@Table(name="product")
@Data
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1280070885115211044L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int price;
	private int account;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Category category;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name="picture", columnDefinition="longblob")
	private byte[] picture;

}
