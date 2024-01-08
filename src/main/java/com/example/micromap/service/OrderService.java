package com.example.micromap.service;

import com.example.micromap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
