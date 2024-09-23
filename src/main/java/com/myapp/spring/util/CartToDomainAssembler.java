package com.myapp.spring.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.myapp.spring.api.model.Cart;
import com.myapp.spring.controller.CartsController;
import com.myapp.spring.domain.CartDomain;
import com.myapp.spring.service.ItemService;

@Component
public class CartToDomainAssembler extends RepresentationModelAssemblerSupport<CartDomain, Cart> {

	
	private final ItemService itemService;
	
	
	public CartToDomainAssembler( ItemService itemService) {
		super(CartsController.class, Cart.class);
		this.itemService = itemService;
	}

	

	@Override
	public Cart toModel(CartDomain entity) {
		
		String uid = Objects.nonNull(entity.getUser())? entity.getUser(): null;
		String cid = Objects.nonNull(entity.getId())? entity.getId(): null;
		
		Cart resource = new Cart();
		BeanUtils.copyProperties(entity.getId(), resource);
		resource.id(cid).customerId(uid).items(itemService.toModelList(entity.getItems()));
		try {
			resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CartsController.class)
					.getCartByCustomerId(uid)).withSelfRel());
			resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CartsController.class)
					.getCartItemsByCustomerId(uid)).withRel("cart-items"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resource;
	}
	
public List<Cart>	toListModel(Iterable<CartDomain> domains){
	if(Objects.isNull(domains)) {
		return List.of();
	}
	return StreamSupport.stream(domains.spliterator(), false).map(this::toModel).collect(Collectors.toList());
}

}
