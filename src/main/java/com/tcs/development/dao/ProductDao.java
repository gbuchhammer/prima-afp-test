package com.tcs.development.dao;

import org.springframework.data.repository.CrudRepository;

import com.tcs.development.domain.models.Product;

public interface ProductDao extends CrudRepository<Product, Long> {

}
