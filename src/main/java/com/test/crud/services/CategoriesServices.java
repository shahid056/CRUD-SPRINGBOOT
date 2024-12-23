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
public class CategoriesServices {

	@Autowired
	private CategoriesReposity categoriesReposity;
	@Autowired
	private ProductRepositry productRepositry;

	// save the products
	public Categories saveCategorey(Categories categorie) {

		
		return categoriesReposity.save(categorie);

	}

	// fetch the single categories
	public Categories getCategories(int categoreyId) {

		Categories categories = categoriesReposity.findById(categoreyId)
				.orElseThrow(() -> new RuntimeException("No Category found with id " + categoreyId));
		return categories;

	}

	// fetch the All product
	 public Page<Categories> getAllCategories(Pageable pageable) {
	        return categoriesReposity.findAll(pageable);
	    }

	// update the Categories

	public Categories updateCategories(int categoriesId, Categories Updatedcategories) {

		Categories categories = categoriesReposity.findById(categoriesId)
				.orElseThrow(() -> new RuntimeException("No Product found by id " + categoriesId));

		categories.setCategoriesName(Updatedcategories.getCategoriesName());

		categories.setProduct(Updatedcategories.getProduct());

		return categoriesReposity.save(categories);

	}

	// delete the categoriess
	public void deleteCategories(int categoriesId) {

		Categories category = categoriesReposity.findById(categoriesId)
				.orElseThrow(() -> new RuntimeException("Category not found by id: " + categoriesId));

		List<Product> list = category.getProduct();

		for (Product product : list) {
			product.setCategories(null);
			productRepositry.save(product);

		}
		categoriesReposity.delete(category);

	}

}
