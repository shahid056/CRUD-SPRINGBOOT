
package com.test.crud.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.crud.entity.Categories;

public interface CategoriesReposity extends JpaRepository<Categories, Integer> {
	Optional<Categories> findByCategoriesName(String categoriesName);

}
