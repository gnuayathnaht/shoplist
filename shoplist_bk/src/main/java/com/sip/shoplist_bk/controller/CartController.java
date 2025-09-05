package com.sip.shoplist_bk.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sip.shoplist_bk.dto.CartDto;
import com.sip.shoplist_bk.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("http://localhost:4200/")
@RequiredArgsConstructor
public class CartController {
	private final CartService cartService;
	
	 	@GetMapping("/view/{userId}")
	    public ResponseEntity<CartDto> viewCart(@PathVariable int userId) {
	        Optional<CartDto>  cart = cartService
	                .getCartByUserId(userId);
	        return cart.map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }
	 	
	 	@PutMapping("/update/{cartItemId}")
	 	public ResponseEntity<Void> updateCartItemQuantity(@PathVariable int cartItemId, @RequestParam int quantity) {
	 		System.out.println("Trying to update quantity");
	 		cartService.updateCartItemQuantity(cartItemId, quantity);
	 		return ResponseEntity.ok().build();
	 	}
	 	
	 	@DeleteMapping("/delete/{itemId}")
	 	public ResponseEntity<Void> removeCartItem(@PathVariable int itemId){
	 		cartService.removeCartItem(itemId);
	 		return ResponseEntity.ok().build();
	 	}
	 

}
