package com.sip.shoplist_bk.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sip.shoplist_bk.dto.CartDto;
import com.sip.shoplist_bk.entity.Cart;
import com.sip.shoplist_bk.entity.CartItem;
import com.sip.shoplist_bk.repo.CartItemRepo;
import com.sip.shoplist_bk.repo.CartRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
	private final CartRepo cartRepo;
	private final CartItemRepo cartItemRepo;
	
	public Optional<CartDto> getCartByUserId(int userId){
		return cartRepo.findByUserId(userId).map(CartDto::new);
	}
	
	 public void updateCartItemQuantity(int cartItemId, int quantity) {
	        CartItem item = cartItemRepo.findById(cartItemId)
	                .orElseThrow(() -> new RuntimeException("Cart item not found"));
	        item.setQuantity(quantity);
	        cartItemRepo.save(item);
	    }

	public void removeCartItem(int itemId) {
		cartItemRepo.deleteById(itemId);
		
	}
	

	public void removeCartItemByUserId(int userId) {
		Optional<Cart> cart = cartRepo.findByUserId(userId);
		if (cart.isPresent()) {
		    System.out.println("Cart found for user " + userId + ", deleting...");
		    cartRepo.deleteByUserId(userId);
		} else {
		    System.out.println("No cart found for user " + userId);
		}

		
	}
}
