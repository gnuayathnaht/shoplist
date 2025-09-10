package com.sip.shoplist_bk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sip.shoplist_bk.dto.CartDto;
import com.sip.shoplist_bk.dto.CartItemDto;
import com.sip.shoplist_bk.entity.Cart;
import com.sip.shoplist_bk.entity.CartItem;
import com.sip.shoplist_bk.service.CartItemService;
import com.sip.shoplist_bk.service.CartService;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("http://localhost:4200/")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;
	private final CartItemService cartItemService;
	ModelMapper mapper = new ModelMapper();

	@GetMapping("/view/{userId}")
	public ResponseEntity<CartDto> viewCart(@PathVariable int userId) {
		Optional<CartDto> cart = cartService.getCartByUserId(userId);
		return cart.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/update/{cartItemId}")
	public ResponseEntity<Void> updateCartItemQuantity(@PathVariable int cartItemId, @RequestParam int quantity) {
		System.out.println("Trying to update quantity");
		cartService.updateCartItemQuantity(cartItemId, quantity);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/delete/{itemId}")
	public ResponseEntity<Void> removeCartItem(@PathVariable int itemId) {
		cartService.removeCartItem(itemId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/save")
	public ResponseEntity<CartDto> saveCartAndCartItem(@RequestBody CartDto cartDto) {
	
	    Cart cart = null;
	    Optional<Cart> result = cartService.getCartByUser(cartDto.getUserId());
	    if(result.isPresent()) {
	    	cart = result.get();
	    }
	    else {
	    	 // Save the Cart
		    cart = cartService.saveCart(cartDto.getUserId());
		    if (cart == null) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		    }
	    }
	   
	    List<CartItem> savedCartItems = new ArrayList<>();
	    for (CartItemDto cartItemDto : cartDto.getItems()) {
	        CartItem cartItem = mapper.map(cartItemDto, CartItem.class);
	        CartItem savedCartItem = cartItemService.saveCartItem(cartItem.getQuantity(), cart, cartItem.getItem());
	        savedCartItems.add(savedCartItem);
	    }

	    cartDto.setItems(savedCartItems.stream()
	            .map(item -> mapper.map(item, CartItemDto.class))
	            .collect(Collectors.toList()));
	    cartDto.setCartId(cart.getId());  
	    cartDto.setUserId(cart.getUser().getId());

	    return ResponseEntity.status(HttpStatus.CREATED).body(cartDto);
	}



}
