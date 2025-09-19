package com.sip.shoplist_bk.dto;

import java.util.List;

import com.sip.shoplist_bk.entity.Order;

public record InvoiceResponse(
    int orderId,
    String userName,
    String userEmail,
    List<OrderItemResponse> items,
    double total
) {
    public static InvoiceResponse from(Order order) {
        List<OrderItemResponse> itemResponses = order.getItems().stream()
                .map(i -> new OrderItemResponse(i.getItem().getName(),i.getQuantity(), i.getPrice()))
                .toList();

        return new InvoiceResponse(
            order.getId(),
            order.getUser().getName(),
            order.getUser().getEmail(),
            itemResponses,
            order.getTotal()
        );
    }
}

record OrderItemResponse(String itemName, int quantity, double price) {}

