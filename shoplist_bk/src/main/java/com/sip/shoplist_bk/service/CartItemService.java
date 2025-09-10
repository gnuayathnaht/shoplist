package com.sip.shoplist_bk.service;

import com.sip.shoplist_bk.entity.Cart;
import com.sip.shoplist_bk.entity.CartItem;
import com.sip.shoplist_bk.entity.Item;
import com.sip.shoplist_bk.repo.CartItemRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
	
	@Autowired
	CartItemRepo cartItemRepo;
	
	public CartItem saveCartItem(int qty, Cart cart, Item item) {
		CartItem cartItem = new CartItem();
		cartItem.setCart(cart);
		cartItem.setItem(item);
		cartItem.setQuantity(qty);

		return cartItemRepo.save(cartItem);
	}

}
