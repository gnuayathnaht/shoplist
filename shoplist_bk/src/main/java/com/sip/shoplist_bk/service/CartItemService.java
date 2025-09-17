package com.sip.shoplist_bk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.shoplist_bk.entity.Cart;
import com.sip.shoplist_bk.entity.CartItem;
import com.sip.shoplist_bk.entity.Item;
import com.sip.shoplist_bk.repo.CartItemRepo;

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

	public CartItem findCartItemByItemIdAndCartId(int itemId, int cartId) {
		return cartItemRepo.findByItemIdAndCartId(itemId, cartId);
		
	}

	public CartItem updateCartItemQTY(int quantity, int itemId, int cartId) {

		CartItem existingCartItem = this.findCartItemByItemIdAndCartId(itemId, cartId);
		System.out.println("existing cart item " + existingCartItem);
		if (existingCartItem != null) {
			
			existingCartItem.setQuantity(quantity + existingCartItem.getQuantity());
			System.out.println("cart item before update " + existingCartItem);
			existingCartItem = cartItemRepo.save(existingCartItem);
		}
		return existingCartItem;
	}

}
