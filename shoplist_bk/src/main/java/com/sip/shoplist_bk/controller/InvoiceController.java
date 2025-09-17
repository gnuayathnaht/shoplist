package com.sip.shoplist_bk.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.shoplist_bk.entity.Order;
import com.sip.shoplist_bk.repo.OrderRepo;
import com.sip.shoplist_bk.service.EmailService;

@RestController
@RequestMapping("/api/invoice")
@CrossOrigin(origins = "http://localhost:4200")
public class InvoiceController {
    private  final OrderRepo orderRepo;
    private  final EmailService emailService;

    public InvoiceController(OrderRepo orderRepo, EmailService emailService) {
        this.orderRepo = orderRepo;
        this.emailService = emailService;
    }

    @PostMapping("/send/{orderId}")
    public String sendInvoiceEmail(@PathVariable int orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow();
        String email = order.getUser().getEmail();

        StringBuilder content = new StringBuilder();
        content.append("Hello ").append(order.getUser().getName()).append(",\n\n");
        content.append("Here is your invoice for Order #").append(order.getId()).append("\n");
        content.append("Date: ").append(order.getOrderDateTime()).append("\n");
        content.append("Total: $").append(order.getTotal()).append("\n\n");
        content.append("Items:\n");
        order.getItems().forEach(item -> {
            content.append("-  Category:: ").append(item.getItem().getCategory().getName())
            	   .append(" | Item:: ").append(item.getItem())
                   .append(" | Qty:: ").append(item.getQuantity())
                   .append(" | Price:: $").append(item.getPrice())
                   .append("\n");
        });

        emailService.sendInvoiceEmail(email, "Invoice for Order #" + order.getId(), content.toString());
        return "Invoice sent successfully to " + email;
    }
}


