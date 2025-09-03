package com.sip.shoplist_bk.dto;


import com.sip.shoplist_bk.entity.Cart;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CartDto {
    private int cartId;
    private int userId;
    private List<CartItemDto> items;
    private double total;

    public CartDto(Cart cart) {
        this.cartId = cart.getId();
        this.userId = cart.getUser().getId();
        this.items = cart.getCartItems().stream()
                .map(CartItemDto::new)
                .collect(Collectors.toList());
        this.total = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}


