package com.test.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.crud.entity.Product;
import com.test.crud.services.ProductServices;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductServices productServices;

	@PostMapping("/products")
	public Product save(@RequestBody Product product) {

		return productServices.saveProduct(product);

	}

	@GetMapping("/products")
	public Page<Product> findAllProduct(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int size) {

		  Pageable pageable = PageRequest.of(page, size);
		return productServices.getAllProduct(pageable);

	}

	@GetMapping("/products/{di}")
	public Product findProductByID(@PathVariable int di) {

		Product product = productServices.getProduct(di);
		System.out.println(product);
		return product;

	}

	@PutMapping("/products/{di}")
	public void UpdateProduct(@PathVariable int di, @RequestBody Product product) {

		productServices.updateProduct(di, product);

	}

	@DeleteMapping("/products/{di}")
	public void DeleteProduct(@PathVariable int di) {

		productServices.deleteProduct(di);

	}

}
