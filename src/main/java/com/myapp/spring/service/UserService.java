package com.myapp.spring.service;

import java.util.Optional;

import com.myapp.spring.domain.UserDomain;



public interface UserService {
  void deleteCustomerById(String id);
  
  Iterable<UserDomain> getAllCustomers();
  
  Optional<UserDomain> getCustomerById(String id);
}
