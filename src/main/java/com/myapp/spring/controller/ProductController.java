package com.myapp.spring.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.api.ProductApi;
import com.myapp.spring.api.model.Product;
import com.myapp.spring.service.ProductService;
import com.myapp.spring.util.ProductToDomainAssembler;

@RestController

public class ProductController implements ProductApi{

	private final ProductService productService;
	private final ProductToDomainAssembler assembler;

	public ProductController(ProductService productService,ProductToDomainAssembler assember) {
		
		this.productService = productService;
		this.assembler = assember;
	}
	
	
	
	public ResponseEntity<List<Product>> queryProducts(){
		return ok(assembler.toListModel(productService.getAllProducts()));
	}
	
	
	public ResponseEntity<Product> getProduct( String id){
		return productService.getProduct(id).map(assembler::toModel).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	

}
