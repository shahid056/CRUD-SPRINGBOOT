package com.test.crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.crud.entity.Categories;
import com.test.crud.entity.Product;
import com.test.crud.repositry.CategoriesReposity;
import com.test.crud.repositry.ProductRepositry;

@Service
public class ProductServices {

	@Autowired
	private ProductRepositry productRepositry;

	@Autowired
	private CategoriesReposity categoriesReposity;

	// save the products
	public Product saveProduct(Product product) {

		System.out.println(product);
		if (product == null || product.getCategories() == null) {
			throw new RuntimeException("Product or category cannot be null");
		}

		List<Categories> categoriesList = categoriesReposity.findAll();

		Categories foundCategory = null;
		for (Categories category : categoriesList) {
			if (category.getCategoriesName().equalsIgnoreCase(product.getCategories().getCategoriesName())) {
				foundCategory = category;
				break;
			}
		}

		if (foundCategory == null) {
			throw new RuntimeException("Category not found: " + product.getCategories().getCategoriesName());
		}

		product.setCategories(foundCategory);

		return productRepositry.save(product);

	}

	// fetch the single product
	public Product getProduct(int productId) {

		return productRepositry.findById(productId).orElseThrow(() -> new RuntimeException("product not found"));

	}

	public Page<Product> getAllProduct(Pageable pageable) {
		return productRepositry.findAll(pageable);
	}

	public Product updateProduct(int productId, Product UpdatedProduct) {

		Product product = productRepositry.findById(productId)
				.orElseThrow(() -> new RuntimeException("No Product found by id " + productId));

		if (UpdatedProduct.getProductName() != null) {
			product.setProductName(UpdatedProduct.getProductName());
		}
		if (UpdatedProduct.getProductPrice() != 0) {
			product.setProductPrice(UpdatedProduct.getProductPrice());
		}

		if (UpdatedProduct.getCategories() != null) {
			Categories updatedCategory = UpdatedProduct.getCategories();

			Categories existingCategory = categoriesReposity.findByCategoriesName(updatedCategory.getCategoriesName())
					.orElseThrow(
							() -> new RuntimeException("Category not found: " + updatedCategory.getCategoriesName()));

			product.setCategories(existingCategory);
		}

		return productRepositry.save(product);

	}

	// delete the product

	public void deleteProduct(int productId) {

		Product product = productRepositry.findById(productId)
				.orElseThrow(() -> new RuntimeException("No Product found by id " + productId));
		product.setCategories(null);
		productRepositry.delete(product);

	}

}
