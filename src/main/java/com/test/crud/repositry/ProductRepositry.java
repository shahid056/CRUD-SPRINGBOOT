package com.test.crud.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.crud.entity.Product;

public interface ProductRepositry extends JpaRepository<Product, Integer>  {

}
