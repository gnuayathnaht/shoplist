package com.sip.shoplist_bk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sip.shoplist_bk.entity.CartItem;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer>{
	
	
}
