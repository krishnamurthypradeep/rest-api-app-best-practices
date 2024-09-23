package com.myapp.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.myapp.spring.domain.UserDomain;


public interface UserRepository extends CrudRepository<UserDomain, String> {
}

