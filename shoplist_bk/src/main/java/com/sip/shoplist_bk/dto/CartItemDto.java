package com.sip.shoplist_bk.dto;

import com.sip.shoplist_bk.entity.CartItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CartItemDto {
    private int id;
    private String categoryName;
    private String orderItem;
    private double price;
    private int quantity;

    public CartItemDto(CartItem cartItem) {
        this.id = cartItem.getId();
        this.categoryName = cartItem.getItem().getCategory().getName();
        this.orderItem = cartItem.getItem().getName();
        this.price = cartItem.getItem().getPrice();
        this.quantity = cartItem.getQuantity();
    }
}

