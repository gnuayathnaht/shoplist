package com.sip.shoplist_bk.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sip.shoplist_bk.dto.CartItemDto;
import com.sip.shoplist_bk.dto.OrderDto;
import com.sip.shoplist_bk.entity.Category;
import com.sip.shoplist_bk.entity.Item;
import com.sip.shoplist_bk.entity.Order;
import com.sip.shoplist_bk.entity.OrderItem;
import com.sip.shoplist_bk.entity.User;
import com.sip.shoplist_bk.repo.CartItemRepo;
import com.sip.shoplist_bk.repo.ItemRepo;
import com.sip.shoplist_bk.repo.OrderRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepo orderRepository;
	private final UserService userService;
	private final CartService cartService;
	private final CartItemRepo cartItemRepo;
	private final CategoryService categoryService;
	private final ItemRepo itemRepo;
	
	@Transactional
	public void checkoutOrder(OrderDto orderDto) {
	
		List<CartItemDto> cartItems = orderDto.getCartItems();
		System.out.println(cartItems);
		if (cartItems.isEmpty()) {
			System.out.println("Cart is empty...");
			return;
		}


	Optional<User> user = userService.findUserById(orderDto.getUserId());
	if(user != null) {
		Order order = new Order();
	    order.setOrderDateTime(LocalDateTime.now());
	    order.setTotal(orderDto.getTotal());
	    order.setPayment(orderDto.getPayment());
	    order.setUser(user.get());
	    cartItems.forEach(cartItem -> {
	        OrderItem orderItem = new OrderItem();
	        orderItem.setQuantity(cartItem.getQuantity());
	        orderItem.setPrice(cartItem.getPrice());
	        Item item = itemRepo.findById(cartItem.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found with id " + cartItem.getId()));
        orderItem.setItem(item);

	        order.addOrderItems(orderItem);
	    });
	    
		Order createdOrder = orderRepository.save(order);
		
		if(createdOrder != null) {
			System.out.println("trying to remove cart with id " + orderDto.getUserId());
			cartItemRepo.deleteByCartId(orderDto.getCartId());
			cartService.removeCartItemByUserId(orderDto.getUserId());
			
		}
	}
    
	}

}
