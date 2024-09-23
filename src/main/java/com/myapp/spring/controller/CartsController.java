package com.myapp.spring.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.api.CartApi;
import com.myapp.spring.api.model.Cart;
import com.myapp.spring.api.model.Item;
import com.myapp.spring.service.CartService;
import com.myapp.spring.util.CartToDomainAssembler;

import jakarta.validation.Valid;

@RestController
public class CartsController implements CartApi{

	private final CartService cartService;
	private final CartToDomainAssembler assembler;
	
	public CartsController(CartService cartService, CartToDomainAssembler assembler) {
		this.cartService = cartService;
		this.assembler = assembler;
	}

	@Override
	public ResponseEntity<List<Item>> addCartItemsByCustomerId(String customerId, @Valid Item item) throws Exception {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(cartService.addCartItemsByCustomerId(customerId, item));
	}
	
	@Override
	public ResponseEntity<List<Item>> addOrReplaceItemsByCustomerId(String customerId, @Valid Item item)
			throws Exception {
		
		return ResponseEntity.ok(cartService.addOrReplaceItemsByCustomerId(customerId, item));
	}
	
	@Override
	public ResponseEntity<Void> deleteCart(String customerId) throws Exception {
		// TODO Auto-generated method stub
	cartService.deleteCart(customerId);
	return ResponseEntity.accepted().build();
	}
	
	@Override
	public ResponseEntity<Void> deleteItemFromCart(String customerId, String itemId) throws Exception {
		// TODO Auto-generated method stub
		cartService.deleteItemFromCart(customerId,itemId);
		return ResponseEntity.accepted().build();
	}
	@Override
	public ResponseEntity<Cart> getCartByCustomerId(String customerId) throws Exception {
		
		return ResponseEntity.ok(assembler.toModel(cartService.getCartByCustomerId(customerId)));
	}
	
	
}

// Restful API best practices

// Statelessness
// Content Negotiation
// URI Templates
// HATEOAS (Discoverability)
// Error and Exception handling
// IDOMPETENT
// SECURITY
// VERSIONING

// Spring Boot MVC
// Spring Data Rest


