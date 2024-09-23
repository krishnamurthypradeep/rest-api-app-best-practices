package com.myapp.spring.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.myapp.spring.domain.UserDomain;
import com.myapp.spring.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public void deleteCustomerById(String id) {
    repository.deleteById(id);
  }



  @Override
  public Iterable<UserDomain> getAllCustomers() {
    return repository.findAll();
  }

 

  @Override
  public Optional<UserDomain> getCustomerById(String id) {
    return repository.findById(id);
  }
}
