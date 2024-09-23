package com.myapp.spring.domain;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;

@Document(collection = "products")
public class ProductDomain {

	@Id
  private String id;

  @NotNull(message = "Product name is required.")
  private String name;


  private String description;


  private Double price;


  private int count;


  private String imageUrl;


 

  

  public ProductDomain() {
  }

  


  public String getId() {
	return id;
}




public ProductDomain setId(String id) {
	this.id = id;
	return this;
}







public String getName() {
    return name;
  }

  public ProductDomain setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public ProductDomain setDescription(String description) {
    this.description = description;
    return this;
  }

  public Double getPrice() {
    return price;
  }

  public ProductDomain setPrice(Double price) {
    this.price = price;
    return this;
  }

  public int getCount() {
    return count;
  }

  public ProductDomain setCount(int count) {
    this.count = count;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public ProductDomain setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

 

 
  }
