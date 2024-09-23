package com.myapp.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.myapp.spring.domain.CartDocument;


public interface CartRepository extends CrudRepository<CartDocument, String> {
  
  Optional<CartDocument> findByCustomerId(@Param("customerId") String customerId);
}
