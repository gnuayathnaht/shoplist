package com.sip.shoplist_bk.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDto {
	private int userId;
	private int cartId;
	private List<CartItemDto> cartItems = new ArrayList<>();
	private double total;
	private String payment;
}
