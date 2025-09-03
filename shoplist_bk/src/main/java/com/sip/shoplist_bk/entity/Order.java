package com.sip.shoplist_bk.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double total;
	private LocalDateTime orderDateTime;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<OrderItem> items = new ArrayList<>();

	public Order(int userId, double total, LocalDateTime orderDateTime) {
		super();
		this.total = total;
		this.orderDateTime = orderDateTime;
	}
	
	public void addOrderItems(OrderItem orderItem) {
		orderItem.setOrder(this);
		this.items.add(orderItem);
	}
}
