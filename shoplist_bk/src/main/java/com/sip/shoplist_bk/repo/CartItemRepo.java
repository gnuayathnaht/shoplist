package com.sip.shoplist_bk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sip.shoplist_bk.entity.CartItem;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer>{
	
	@Modifying
	@Query("DELETE FROM CartItem c WHERE c.cart.id = :cartId")
	void deleteByCartId(@Param("cartId")int cartId);
}
