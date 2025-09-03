package com.sip.shoplist_bk.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
