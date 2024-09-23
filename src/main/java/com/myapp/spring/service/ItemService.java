package com.myapp.spring.service;

import java.util.List;

import com.myapp.spring.api.model.Item;
import com.myapp.spring.domain.ItemDomain;


public interface ItemService {

  ItemDomain toEntity(Item m);

  List<ItemDomain> toEntityList(List<Item> items);

  Item toModel(ItemDomain e);

  List<Item> toModelList(List<ItemDomain> items);
}
