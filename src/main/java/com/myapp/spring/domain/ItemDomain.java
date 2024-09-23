package com.myapp.spring.domain;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Document(collection = "items")
public class ItemDomain {

 
	@Id
  private String id;

  
  private ProductDomain product;

  
  private Double price;

  
  private int quantity;

  private String customerId;


  
  

  
  public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getCustomerId() {
	return customerId;
}

public void setCustomerId(String customerId) {
	this.customerId = customerId;
}

public ProductDomain getProduct() {
    return product;
  }

  public ItemDomain setProduct(ProductDomain product) {
    this.product = product;
    return this;
  }

  public Double getPrice() {
    return price;
  }

  public ItemDomain setPrice(Double price) {
    this.price = price;
    return this;
  }

  public int getQuantity() {
    return quantity;
  }

  public ItemDomain setQuantity(int quantity) {
    this.quantity = quantity;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemDomain that = (ItemDomain) o;
    return quantity == that.quantity && product.equals(that.product) && Objects
        .equals(price, that.price);// && Objects.equals(cart, that.cart);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, price, quantity);//, cart);
  }



}
