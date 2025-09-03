package com.sip.shoplist_bk.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sip.shoplist_bk.dto.CartItemDto;
import com.sip.shoplist_bk.dto.OrderDto;
import com.sip.shoplist_bk.entity.Order;
import com.sip.shoplist_bk.entity.OrderItem;
import com.sip.shoplist_bk.entity.User;
import com.sip.shoplist_bk.repo.OrderRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepo orderRepository;
	private final UserService userService;
	
	public void checkoutOrder(OrderDto orderDto) {
	
		List<CartItemDto> cartItems = orderDto.getCartItems();
		if (cartItems.isEmpty()) {
			System.out.println("Cart is empty...");
			return;
		}


	Optional<User> user = userService.findUserById(orderDto.getUserId());

    Order order = new Order();
    order.setOrderDateTime(LocalDateTime.now());
    order.setTotal(orderDto.getTotal());
    user.ifPresent(order::setUser); 

   
    cartItems.forEach(cartItem -> {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(cartItem.getOrderItem());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setPrice(cartItem.getPrice());
        order.addOrderItems(orderItem);
    });

	orderRepository.save(order);	
	}

}
