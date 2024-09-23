package com.myapp.spring.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "carts")
public class CartDomain {

  @Id
  private String id;

 
  private String user;
  
  private String customerId;

  
  private List<ItemDomain> items = new ArrayList<>();
  
  

  public String getUser() {
	return user;
}

public void setUser(String user) {
	this.user = user;
}

public String getId() {
    return id;
  }

  public CartDomain setId(String id) {
    this.id = id;
    return this;
  }

  

  public List<ItemDomain> getItems() {
    return items;
  }

  public CartDomain setItems(List<ItemDomain> items) {
    this.items = items;
    return this;
  }
  
  

  public String getCustomerId() {
	return customerId;
}

public void setCustomerId(String customerId) {
	this.customerId = customerId;
}

@Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartDomain that = (CartDomain) o;
    return user.equals(that.user) && Objects.equals(items, that.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, items);
  }
}
