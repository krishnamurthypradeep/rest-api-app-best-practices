package com.myapp.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.myapp.spring.domain.ItemDomain;

public interface ItemRepository extends CrudRepository<ItemDomain, String> {
  Iterable<ItemDomain> findByCustomerId(String customerId);

  
 
  void deleteCartItemJoinById(List<String> ids, String cartId);
}
