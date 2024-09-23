package com.myapp.spring.service;

import static java.util.stream.Collectors.toList;
import static org.springframework.objenesis.instantiator.util.UnsafeUtils.getUnsafe;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Service;

import com.myapp.spring.api.model.Item;
import com.myapp.spring.domain.CartDomain;
import com.myapp.spring.domain.ItemDomain;
import com.myapp.spring.exception.GenericAlreadyExistsException;
import com.myapp.spring.exception.ItemNotFoundException;
import com.myapp.spring.repository.CartRepository;

import jakarta.validation.Valid;

@Service
public class CartServiceImpl implements CartService {

  private final CartRepository repository;
  
  private final ItemService itemService;

  public CartServiceImpl(CartRepository repository, 
      ItemService itemService) {
    this.repository = repository;
  
    this.itemService = itemService;
  }

  @Override
  public List<Item> addCartItemsByCustomerId(String customerId, @Valid Item item) {
    CartDomain entity = getCartByCustomerId(customerId);
    long count = entity.getItems().stream()
        .filter(i -> i.getProduct().getId().equals(item.getId())).count();
    if (count > 0) {
      throw new GenericAlreadyExistsException(
          String.format("Item with Id (%s) already exists. You can update it.", item.getId()));
    }
    entity.getItems().add(itemService.toEntity(item));
    return itemService.toModelList(repository.save(entity).getItems());
  }

  @Override
  public List<Item> addOrReplaceItemsByCustomerId(String customerId, @Valid Item item) {
    CartDomain entity = getCartByCustomerId(customerId);
    List<ItemDomain> items =
        Objects.nonNull(entity.getItems()) ? entity.getItems() : List.of();
    AtomicBoolean itemExists = new AtomicBoolean(false);
    items.forEach(i -> {
      if (i.getProduct().getId().equals(item.getId())) {
        i.setQuantity(item.getQuantity()).setPrice(i.getPrice());
        itemExists.set(true);
      }
    });
    if (!itemExists.get()) {
      items.add(itemService.toEntity(item));
    }
    return itemService.toModelList(repository.save(entity).getItems());
  }

  @Override
  public void deleteCart(String customerId) {
    // will throw the error if it doesn't exist
    CartDomain entity = getCartByCustomerId(customerId);
    repository.deleteById(entity.getId());
  }

  @Override
  public void deleteItemFromCart(String customerId, String itemId) {
    CartDomain entity = getCartByCustomerId(customerId);
    List<ItemDomain> updatedItems = entity.getItems().stream()
        .filter(i -> !i.getProduct().getId().equals(UUID.fromString(itemId))).collect(toList());
    entity.setItems(updatedItems);
    repository.save(entity);
  }

  @Override
  public CartDomain getCartByCustomerId(String customerId) {
    CartDomain entity = repository.findByCustomerId(customerId)
        .orElse(new CartDomain());
    
    return entity;
  }

  @Override
  public List<Item> getCartItemsByCustomerId(String customerId) {
    CartDomain entity = getCartByCustomerId(customerId);
    return itemService.toModelList(entity.getItems());
  }

  @Override
  public Item getCartItemsByItemId(String customerId, String itemId) {
    CartDomain entity = getCartByCustomerId(customerId);
    AtomicReference<ItemDomain> itemEntity = new AtomicReference<>();
    entity.getItems().forEach(i -> {
      if (i.getProduct().getId().equals(itemId)) {
        itemEntity.set(i);
      }
    });
    if (Objects.isNull(itemEntity.get())) {
      getUnsafe().throwException(new ItemNotFoundException(String.format(" - %s", itemId)));
    }
    return itemService.toModel(itemEntity.get());
  }
}
