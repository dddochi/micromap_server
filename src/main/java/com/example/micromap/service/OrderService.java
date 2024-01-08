package com.example.micromap.service;

import com.example.micromap.domain.Order;
import com.example.micromap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //Create Order
    public Long createOrder(Order order){
        return orderRepository.createOrder(order);
    }
    //Update Order
    public String updateOrderToAccepted(Long order_id, Long restaurant_id, boolean is_accepted){
        return orderRepository.acceptOrder(order_id, restaurant_id, is_accepted);
    }
    public String updateOrderToFinished(Long order_id, Long restaurant_id, boolean is_finished){
        return orderRepository.finishOrder(order_id, restaurant_id, is_finished);
    }
    public String updateOrderToTaken(Long order_id, Long restaurant_id, boolean is_taken){
        return orderRepository.takenOrder(order_id, restaurant_id, is_taken);
    }

    //Select order records
    public List<Order> userOrderRecords(String user_id){
        return orderRepository.selectOrderRecordsOfUser(user_id);
    }
    public List<Order> restaurantOrderRecords(Long restaurant_id){
        return orderRepository.selectOrderRecordsOfRestaurant(restaurant_id);
    }
    public List<Order> userOrderRecordsByDate(String user_id, LocalDateTime date){
        return orderRepository.selectOrderRecordsOfUserByDate(user_id, date);
    }
    public List<Order> restaurantOrderRecordsByDate(Long restaurant_id, LocalDateTime date){
        return orderRepository.selectOrderRecordsOfRestaurantByDate(restaurant_id, date);
    }
    public List<Order> userOrderRecordsByNow(String user_id, LocalDateTime date){
        return  orderRepository.selectOrderListOfUserByNow(user_id, date);
    }
    public List<Order> restaurantOrderRecordsByNow(Long restaurant_id, LocalDateTime date){
        return orderRepository.selectOrderListOfRestaurantByNow(restaurant_id, date);
    }
}
