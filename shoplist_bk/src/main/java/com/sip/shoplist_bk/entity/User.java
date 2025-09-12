package com.sip.shoplist_bk.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "users") 
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;    
	@Column(unique = true, nullable = false)
	private String email;       
	private String password;    
	private String phone;      
	private String address;
	
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Cart cart;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
    private List<Order> orders = new ArrayList<>();
	
	public void addOrder(Order order) {
		order.setUser(this);
		orders.add(order);
	}
	
	

}