package com.myapp.spring.service;

import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import com.myapp.spring.domain.ProductDomain;

import jakarta.validation.constraints.NotNull;


@Validated
public interface ProductService {
  @NotNull Iterable<ProductDomain> getAllProducts();
  Optional<ProductDomain> getProduct( String id);
}