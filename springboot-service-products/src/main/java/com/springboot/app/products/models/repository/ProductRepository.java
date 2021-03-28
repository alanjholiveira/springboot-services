package com.springboot.app.products.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.commons.domain.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
