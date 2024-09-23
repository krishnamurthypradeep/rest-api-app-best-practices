package com.myapp.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.myapp.spring.domain.CartDomain;


public interface CartRepository extends CrudRepository<CartDomain, String> {
  
  Optional<CartDomain> findByCustomerId(@Param("customerId") String customerId);
}
