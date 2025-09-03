package com.sip.shoplist_bk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.shoplist_bk.dto.OrderDto;
import com.sip.shoplist_bk.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("http://localhost:4200/")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping("/create")
	public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto orderDto ) {
		System.out.println("trying to save order....." + orderDto.toString());
		orderService.checkoutOrder(orderDto);
		
		return ResponseEntity.ok(orderDto);
	}

}
