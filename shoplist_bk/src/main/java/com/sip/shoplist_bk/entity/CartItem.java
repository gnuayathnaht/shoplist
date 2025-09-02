package com.sip.shoplist_bk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int itemId;
	private String categoryName;
	private String orderItem;
	private double price;
	private int quantity;
	
	public CartItem(int itemId, String categoryName, String orderItem, double price, int quantity) {
		super();
		this.itemId = itemId;
		this.categoryName = categoryName;
		this.orderItem = orderItem;
		this.price = price;
		this.quantity = quantity;
	}
	
	@OneToOne
    @JoinColumn(name = "user_id")
    private User user;

	
	
}

	
	

