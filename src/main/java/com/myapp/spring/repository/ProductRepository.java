package com.myapp.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.myapp.spring.domain.ProductDomain;


public interface ProductRepository extends CrudRepository<ProductDomain, String> {
}

