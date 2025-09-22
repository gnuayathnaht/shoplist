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
    private int cartItemId;
    private String categoryName;
    private int itemId;
    private String orderItemName;
    private double price;
    private int quantity;
    private String imageUrl;

    public CartItemDto(CartItem cartItem) {
        this.cartItemId = cartItem.getId();
        this.categoryName = cartItem.getItem().getCategory().getName();
        this.itemId = cartItem.getItem().getId();
        this.orderItemName = cartItem.getItem().getName();
        this.price = cartItem.getItem().getPrice();
        this.quantity = cartItem.getQuantity();
        this.imageUrl = cartItem.getItem().getImagePath();
    }
}

