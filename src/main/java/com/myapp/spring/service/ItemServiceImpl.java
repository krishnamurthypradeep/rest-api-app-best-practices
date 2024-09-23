package com.myapp.spring.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.myapp.spring.api.model.Item;
import com.myapp.spring.domain.ItemDomain;
import com.myapp.spring.domain.ProductDomain;


@Service
public class ItemServiceImpl implements ItemService {

  @Override
  public ItemDomain toEntity(Item m) {
	  
    ItemDomain e = new ItemDomain();
    e.setProduct(new ProductDomain().setId(m.getId()))
        .setPrice(m.getUnitPrice())
        .setQuantity(m.getQuantity());
    return e;
  }

  @Override
  public List<ItemDomain> toEntityList(List<Item> items) {
    if (Objects.isNull(items)) {
      return List.of();
    }
    return items.stream().map(this::toEntity).collect(toList());
  }

  @Override
  public Item toModel(ItemDomain e) {
    Item m = new Item();
    m.id(e.getProduct().getId().toString()).unitPrice(e.getPrice()).quantity(e.getQuantity());
    return m;
  }

  @Override
  public List<Item> toModelList(List<ItemDomain> items) {
    if (Objects.isNull(items)) {
      return List.of();
    }
    return items.stream().map(this::toModel).collect(toList());
  }
}
