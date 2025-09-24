package com.sip.shoplist_bk.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.shoplist_bk.dto.InvoiceResponse;
import com.sip.shoplist_bk.dto.OrderDetailResponse;
import com.sip.shoplist_bk.entity.Order;
import com.sip.shoplist_bk.repo.OrderRepo;

@RestController
@RequestMapping("/api/orders")
public class OrderHistoryController {
    private final OrderRepo orderRepo;

    public OrderHistoryController(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/user/{userId}")
    public List<Order> findOrdersByUserId(@PathVariable int userId) {
        return orderRepo.findByUserId(userId);
    }

    @GetMapping("/{orderId}")
    public OrderDetailResponse getOrderDetails(@PathVariable int orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow();
        return OrderDetailResponse.from(order);
    }

    @GetMapping("/{orderId}/invoice")
    public InvoiceResponse getInvoice(@PathVariable int orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow();
        return InvoiceResponse.from(order);
    }
}