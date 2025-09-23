package com.sip.shoplist_bk.controller;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

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
        DecimalFormat df = new DecimalFormat("#,###.##");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a");

        StringBuilder content = new StringBuilder();
        content.append("Hello ").append(order.getUser().getName()).append(",\n\n");
        content.append("Here is your invoice for Order- ").append(order.getId()).append("\n");
        content.append("Date: ").append(order.getOrderDateTime().format(formatter)).append("\n");
        content.append("Total: $").append(df.format(order.getTotal())).append("\n\n");
        content.append(" List of Products:: \n");
        order.getItems().forEach(item -> {
            content.append(" Item:: ").append(item.getItem().getName())
                   .append(" | Quantity:: ").append(item.getQuantity())
                   .append(" | Price:: $ ").append(df.format(item.getPrice()))
                   .append("\n");
        });

        emailService.sendInvoiceEmail(email, "Invoice for Order #" + order.getId(), content.toString());
        return "Invoice sent successfully to " + email;
    }
}


