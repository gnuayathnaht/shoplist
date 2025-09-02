package com.sip.shoplist_bk.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sip.shoplist_bk.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);


}
