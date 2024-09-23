package com.myapp.spring.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.spring.domain.ProductDomain;
import com.myapp.spring.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;

  public ProductServiceImpl(ProductRepository repository) {
    this.repository = repository;
  }

  @Override
  public Iterable<ProductDomain> getAllProducts() {
    return repository.findAll();
  }

  @Override
  public Optional<ProductDomain> getProduct(String id) {
    return repository.findById(id);
  }
}
