package com.sip.shoplist_bk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String item;
	private int quantity;
	private double price;

	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonIgnore
	private Category category;
	
	
}

