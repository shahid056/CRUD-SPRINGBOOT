
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

import com.test.crud.entity.Categories;
import com.test.crud.services.CategoriesServices;

@RestController
@RequestMapping("/api")
public class CategoreyController {

	@Autowired
	private CategoriesServices categoriesServices;

	@PostMapping("/categories")
	public Categories save(@RequestBody Categories categories) {

		return categoriesServices.saveCategorey(categories);

	}

	@GetMapping("/categories")
	  public Page<Categories> getAllCategories(
		        @RequestParam(value = "pageNumber",defaultValue  = "0",required = false) int page,
		        @RequestParam(value = "pageSize",defaultValue = "10",required = false) int size
		    ) {
		       Pageable pageable = PageRequest.of(page, size);
		        return categoriesServices.getAllCategories(pageable);
		    }

	@GetMapping("/categories/{di}")
	public Categories findCategories(@PathVariable int di) {

		return categoriesServices.getCategories(di);

	}

	@PutMapping("/categories/{di}")
	public void updateCategories(@PathVariable int di,@RequestBody Categories categories) {

		categoriesServices.updateCategories(di, categories);

	}

	@DeleteMapping("/categories/{di}")
	public void deleteCategorie(@PathVariable int di) {

		categoriesServices.deleteCategories(di);

	}

}
