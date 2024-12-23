package com.test.crud.entity;


import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Categories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoriesId;
	@Column(nullable = false,unique = true)
	private String categoriesName;
	@OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)

	private List<Product> product  = new ArrayList<>();
	
	
	
	
	public int getId() {
		return categoriesId;
	}
	public void setId(int id) {
		this.categoriesId = id;
	}
	public String getCategoriesName() {
		return categoriesName;
	}
	public void setCategoriesName(String categoriesName) {
		this.categoriesName = categoriesName;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categories(String categoriesName, List<Product> product) {
		super();
		this.categoriesName = categoriesName;
		this.product = product;
	}
	public Categories(String categoriesName) {
		super();
		this.categoriesName = categoriesName;
	}
	
	
	
	
	
	
	
}
