package com.myapp.spring.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.myapp.spring.api.model.Product;
import com.myapp.spring.controller.ProductController;
import com.myapp.spring.domain.ProductDomain;

@Component
public class ProductToDomainAssembler extends RepresentationModelAssemblerSupport<ProductDomain, Product> {

	public ProductToDomainAssembler() {
		super(ProductController.class, Product.class);
	}

	@Override
	public Product toModel(ProductDomain entity) {
		
		Product resource = createModelWithId(entity.getId(), entity);
		BeanUtils.copyProperties(entity.getId(), resource);
		resource.setId(entity.getId());
		resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class)
				.getProduct(entity.getId())).withSelfRel());
		
		return resource;
	}
	
public List<Product>	toListModel(Iterable<ProductDomain> domains){
	if(Objects.isNull(domains)) {
		return List.of();
	}
	return StreamSupport.stream(domains.spliterator(), false).map(this::toModel).collect(Collectors.toList());
}

}
