package com.sip.shoplist_bk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String category;
	private int quantity;
	private double price;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	public OrderItem(String category, int quantity, double price) {
		super();
		this.category = category;
		this.quantity = quantity;
		this.price = price;
	}

}