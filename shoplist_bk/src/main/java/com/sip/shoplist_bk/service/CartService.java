package com.sip.shoplist_bk.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sip.shoplist_bk.dto.CartDto;
import com.sip.shoplist_bk.repo.CartRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
	private final CartRepo cartRepo;
	
	public Optional<CartDto> getCartByUserId(int userId){
		return cartRepo.findByUserId(userId).map(CartDto::new);
	}
}
