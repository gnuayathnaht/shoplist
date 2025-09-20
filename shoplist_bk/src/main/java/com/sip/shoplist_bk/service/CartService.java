package com.sip.shoplist_bk.service;

import java.util.Optional;

import com.sip.shoplist_bk.dto.CartDto;
import com.sip.shoplist_bk.entity.Cart;
import com.sip.shoplist_bk.entity.CartItem;
import com.sip.shoplist_bk.entity.User;
import com.sip.shoplist_bk.repo.CartItemRepo;
import com.sip.shoplist_bk.repo.CartRepo;
import com.sip.shoplist_bk.repo.UserRepo;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
	private final CartRepo cartRepo;
	private final CartItemRepo cartItemRepo;
	private final UserRepo userRepo;
	
	public Optional<CartDto> getCartByUserId(int userId){
		return cartRepo.findByUserId(userId).map(CartDto::new);
	}
	
	public Optional<Cart> findCartByUserId(int userId) {
		return cartRepo.findByUserId(userId);
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
	
	public Cart saveCart(int userId) {
		Optional<User> data  = userRepo.findById(userId);
		Cart cart = null;
		if(data!=null) {
			User user = data.get();
			
			cart = new Cart();
			cart.setUser(user);
			cart = cartRepo.save(cart);
		}
		
		return cart;
	}
}
