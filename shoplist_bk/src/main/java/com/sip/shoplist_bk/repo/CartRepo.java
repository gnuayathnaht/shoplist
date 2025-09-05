package com.sip.shoplist_bk.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sip.shoplist_bk.entity.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	 Optional<Cart> findByUserId(int userId);
	 
	 @Modifying
	 @Query("DELETE FROM Cart c WHERE c.user.id = :userId")
	 void deleteByUserId(@Param("userId") int userId);
}
