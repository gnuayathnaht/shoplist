package com.sip.shoplist_bk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", quantity=" + quantity + ", cart=" + cart + ", item=" + item + "]";
	}

	
	
}

	
	

