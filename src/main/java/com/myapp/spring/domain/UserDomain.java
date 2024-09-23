package com.myapp.spring.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;


@Document(collection = "users")
public class UserDomain {
	
@Id	
  private String id;

  @NotNull(message = "User name is required.")
  private String username;


  private String password;


  private String firstName;


  private String lastName;


  private String email;


  private String phone;


  private String userStatus;

  
  public String getUsername() {
    return username;
  }

  public UserDomain setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserDomain setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserDomain setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserDomain setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserDomain setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public UserDomain setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public String getUserStatus() {
    return userStatus;
  }

  public UserDomain setUserStatus(String userStatus) {
    this.userStatus = userStatus;
    return this;
  }

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

  
}
