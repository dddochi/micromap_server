package com.example.micromap.controller;

import com.example.micromap.domain.Order;
import com.example.micromap.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Tag(name = "order_API", description = "Swagger Test API For Order")
@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create_order")
    public Long createOrder(@RequestBody Map<String, Object> requestData){
        String user_id = (String) requestData.get("user_id");
        Long restaurant_id = (Long) requestData.get("restaurant_id");
        LocalDateTime created_at = (LocalDateTime) requestData.get("created_at");
        double price = (double) requestData.get("price");
        Boolean is_pay = (Boolean) requestData.get("is_pay");
        String pay_info = (String) requestData.get("pay_info");

        Order order = new Order();
        order.setUserId(user_id);
        order.setRestaurantId(restaurant_id);
        order.setCreatedAt(created_at);
        order.setPrice(price);
        order.setPay(is_pay);
        order.setPayInfo(pay_info);

        order.setAccepted(false);
        order.setFinished(false);
        order.setTaken(false);

        return orderService.createOrder(order);
    }

    @PostMapping("update_accepted")
    public String updateAccepted(@RequestBody Map<String, Object> requestData){
        Long order_id = (Long) requestData.get("order_id");
        Long restaurant_id = (Long) requestData.get("restaurant_id");
        Boolean is_accepted = (Boolean) requestData.get("is_accepted");

        return orderService.updateOrderToAccepted(order_id, restaurant_id, is_accepted);
    }

    @PostMapping("update_finished")
    public String updateFinished(@RequestBody Map<String, Object> requestData){
        Long order_id = (Long) requestData.get("order_id");
        Long restaurant_id = (Long) requestData.get("restaurant_id");
        Boolean is_finished = (Boolean) requestData.get("is_finished");

        return orderService.updateOrderToFinished(order_id, restaurant_id, is_finished);
    }

    @PostMapping("update_taken")
    public String updateTaken(@RequestBody Map<String, Object> requestData){
        Long order_id = (Long) requestData.get("order_id");
        Long restaurant_id = (Long) requestData.get("restaurant_id");
        Boolean is_taken = (Boolean) requestData.get("is_taken");

        return orderService.updateOrderToTaken(order_id, restaurant_id, is_taken);
    }

    @GetMapping("user_order_records")
    public List<Order> userOrderRecords(@RequestParam(name = "user_id") String user_id){
        return orderService.userOrderRecords(user_id);
    }

    @GetMapping("restaurant_order_records")
    public List<Order> restaurantOrderRecords(@RequestParam(name = "restaurant_id") Long restaurant_id){
        return orderService.restaurantOrderRecords(restaurant_id);
    }

    @GetMapping("user_order_records_by_date")
    public List<Order> userOrderRecordsByDate(@RequestParam Map<String, Object> requestParam){
        String user_id = (String) requestParam.get("user_id");
        LocalDateTime date = (LocalDateTime) requestParam.get("date");
        return orderService.userOrderRecordsByDate(user_id, date);
    }

    @GetMapping("restaurant_order_records_by_date")
    public List<Order> restaurantOrderRecordsByDate(@RequestParam Map<String, Object> requestParam){
        Long restaurant_id = (Long) requestParam.get("restaurant_id");
        LocalDateTime date = (LocalDateTime) requestParam.get("date");
        return orderService.restaurantOrderRecordsByDate(restaurant_id, date);
    }

    @GetMapping("user_order_list_now")
    public List<Order> userOrderListNow(@RequestParam Map<String, Object> requestParam){
        String user_id = (String) requestParam.get("user_id");
        LocalDateTime date = (LocalDateTime) requestParam.get("date");
        return orderService.userOrderRecordsByNow(user_id, date);
    }
    @GetMapping("restaurant_order_list_now")
    public List<Order> restaurantOrderListNow(@RequestParam Map<String, Object> requestParam){
        Long restaurant_id = (Long) requestParam.get("restaurant_id");
        LocalDateTime date = (LocalDateTime) requestParam.get("date");
        return orderService.restaurantOrderRecordsByNow(restaurant_id, date);
    }

}
