package com.sip.shoplist_bk.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.sip.shoplist_bk.entity.Order;

public record OrderDetailResponse(int orderId, LocalDateTime dateTime, List<DetailItemResponse> items, double total) {

	public static OrderDetailResponse from(Order order) {
        List<DetailItemResponse> itemResponses = order.getItems().stream()
                .map(i -> new DetailItemResponse(i.getCategory().getName(),i.getItem(), i.getQuantity(), i.getPrice()))
                .toList();

        return new OrderDetailResponse(
            order.getId(),
            order.getOrderDateTime(),
            itemResponses,
            order.getTotal()
        );
    }
}
record DetailItemResponse(String categoryName,String itemName, int quantity, double price) {}