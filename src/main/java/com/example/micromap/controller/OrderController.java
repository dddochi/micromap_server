package com.example.micromap.controller;

import com.example.micromap.domain.Order;
import com.example.micromap.service.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

//    @PostMapping("/create_order")
//    public Long createOrder(@RequestBody Map<String, Object> requestData){
//        String user_id = (String) requestData.get("user_id");
//        Long restaurant_id = (Long) requestData.get("restaurant_id");
//        LocalDateTime created_at = (LocalDateTime) requestData.get("created_at");
//        double price = (double) requestData.get("price");
//        Boolean is_pay = (Boolean) requestData.get("is_pay");
//        String pay_info = (String) requestData.get("pay_info");
//
//        Order order = new Order();
//        order.setUserId(user_id);
//        order.setRestaurantId(restaurant_id);
//        order.setCreatedAt(created_at);
//        order.setPrice(price);
//        order.setPay(is_pay);
//        order.setPayInfo(pay_info);
//
//        order.setAccepted(false);
//        order.setFinished(false);
//        order.setTaken(false);
//
//        return orderService.createOrder(order);
//    }

    @RequestMapping(value = "/create_order/{user_id}/{restaurant_id}/{created_at}/{price}/{is_pay}/{pay_info}", method = RequestMethod.POST)
    public Long createOrder(
            @Parameter(description = "유저 아이디")
            @PathVariable(name = "user_id", required = true) String user_id,
            @Parameter(description = "식당 아이디")
            @PathVariable(name = "restaurant_id", required = true) Long restaurant_id,
            @Parameter(description = "Created At", example = "2022-01-17T15:45:30")
            @PathVariable(name = "created_at", required = true) LocalDateTime created_at,
            @Parameter(description = "가격")
            @PathVariable(name = "price", required = true) double price,
            @Parameter(description = "is_pay", example = "true")
            @PathVariable(name = "is_pay", required = true) Boolean is_pay,
            @Parameter(description = "결제 정보")
            @PathVariable(name = "pay_info", required = true) String pay_info
            ){

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


//    @PostMapping("update_accepted")
//    public String updateAccepted(@RequestBody Map<String, Object> requestData){
//        Long order_id = (Long) requestData.get("order_id");
//        Long restaurant_id = (Long) requestData.get("restaurant_id");
//        Boolean is_accepted = (Boolean) requestData.get("is_accepted");
//
//        return orderService.updateOrderToAccepted(order_id, restaurant_id, is_accepted);
//    }
@RequestMapping(value = "update_accepted/{order_id}/{restaurant_id}/{is_accepted}", method = RequestMethod.POST)
public String updateAccepted(
        @Parameter(description = "주문 아이디")
        @PathVariable(name = "order_id", required = true) Long order_id,
        @Parameter(description = "식당 아이디")
        @PathVariable(name = "restaurant_id", required = true) Long restaurant_id,
        @Parameter(description = "is_accepted?")
        @PathVariable(name = "is_accepted", required = true) Boolean is_accepted
){

    return orderService.updateOrderToAccepted(order_id, restaurant_id, is_accepted);
}

//    @PostMapping("update_finished")
//    public String updateFinished(@RequestBody Map<String, Object> requestData){
//        Long order_id = (Long) requestData.get("order_id");
//        Long restaurant_id = (Long) requestData.get("restaurant_id");
//        Boolean is_finished = (Boolean) requestData.get("is_finished");
//
//        return orderService.updateOrderToFinished(order_id, restaurant_id, is_finished);
//    }
@RequestMapping(value = "update_finished/{order_id}/{restaurant_id}/{is_accepted}", method = RequestMethod.POST)
public String updateFinished(
        @Parameter(description = "주문 아이디")
        @PathVariable(name = "order_id", required = true) Long order_id,
        @Parameter(description = "식당 아이디")
        @PathVariable(name = "restaurant_id", required = true) Long restaurant_id,
        @Parameter(description = "is_accepted?")
        @PathVariable(name = "is_accepted", required = true) Boolean is_accepted
){

    return orderService.updateOrderToFinished(order_id, restaurant_id, is_accepted);
}

//    @PostMapping("update_taken")
//    public String updateTaken(@RequestBody Map<String, Object> requestData){
//        Long order_id = (Long) requestData.get("order_id");
//        Long restaurant_id = (Long) requestData.get("restaurant_id");
//        Boolean is_taken = (Boolean) requestData.get("is_taken");
//
//        return orderService.updateOrderToTaken(order_id, restaurant_id, is_taken);
//    }
@RequestMapping(value = "update_taken/{order_id}/{restaurant_id}/{is_accepted}", method = RequestMethod.POST)
public String updateTaken(
        @Parameter(description = "주문 아이디")
        @PathVariable(name = "order_id", required = true) Long order_id,
        @Parameter(description = "식당 아이디")
        @PathVariable(name = "restaurant_id", required = true) Long restaurant_id,
        @Parameter(description = "is_accepted?")
        @PathVariable(name = "is_accepted", required = true) Boolean is_accepted
){

    return orderService.updateOrderToTaken(order_id, restaurant_id, is_accepted);
}

//    @GetMapping("user_order_records")
//    public List<Order> userOrderRecords(@RequestParam(name = "user_id") String user_id){
//        return orderService.userOrderRecords(user_id);
//    }
@RequestMapping(value = "user_order_records/{user_id}", method = RequestMethod.GET)
public List<Order> userOrderRecords(
                                    @Parameter(description = "유저 아이디")
                                    @PathVariable(name = "user_id", required = true) String user_id){
    return orderService.userOrderRecords(user_id);
}

//    @GetMapping("restaurant_order_records")
//    public List<Order> restaurantOrderRecords(@RequestParam(name = "restaurant_id") Long restaurant_id){
//        return orderService.restaurantOrderRecords(restaurant_id);
//    }

@RequestMapping(value = "restaurant_order_records/{restaurant_id}", method = RequestMethod.GET)
public List<Order> restaurantOrderRecords(
    @Parameter(description = "식당 아이디")
    @PathVariable(name = "restaurant_id", required = true) Long restaurant_id){
    return orderService.restaurantOrderRecords(restaurant_id);
}

//    @GetMapping("user_order_records_by_date")
//    public List<Order> userOrderRecordsByDate(@RequestParam Map<String, Object> requestParam){
//        String user_id = (String) requestParam.get("user_id");
//        LocalDateTime date = (LocalDateTime) requestParam.get("date");
//        return orderService.userOrderRecordsByDate(user_id, date);
//    }
@RequestMapping(value = "user_order_records_by_date/{user_id}/{date}", method = RequestMethod.GET)
public List<Order> userOrderRecordsByDate(
        @Parameter(description="유저 아이디")
        @PathVariable(name="user_id", required = true) String user_id,
        @Parameter(description = "날짜", example = "2022-01-17T15:45:30")
        @PathVariable(name = "date", required = true) LocalDateTime date
                                          ){
    return orderService.userOrderRecordsByDate(user_id, date);
}

//    @GetMapping("restaurant_order_records_by_date")
//    public List<Order> restaurantOrderRecordsByDate(@RequestParam Map<String, Object> requestParam){
//        Long restaurant_id = (Long) requestParam.get("restaurant_id");
//        LocalDateTime date = (LocalDateTime) requestParam.get("date");
//        return orderService.restaurantOrderRecordsByDate(restaurant_id, date);
//    }
@RequestMapping(value = "restaurant_order_records_by_date/{restaurant_id}/{date}", method = RequestMethod.GET)
public List<Order> restaurantOrderRecordsByDate(
        @Parameter(description = "식당 아이디")
        @PathVariable(name = "restaurant_id") Long restaurant_id,
        @Parameter(description = "date", example = "2022-01-17T15:45:30")
        @PathVariable(name = "date", required = true) LocalDateTime date
){
    return orderService.restaurantOrderRecordsByDate(restaurant_id, date);
}

//    @GetMapping("user_order_list_now")
//    public List<Order> userOrderListNow(@RequestParam Map<String, Object> requestParam){
//        String user_id = (String) requestParam.get("user_id");
//        LocalDateTime date = (LocalDateTime) requestParam.get("date");
//        return orderService.userOrderRecordsByNow(user_id, date);
//    }

@RequestMapping(value ="user_order_list_now/{user_id}/{date}", method = RequestMethod.GET)
public List<Order> userOrderListNow(
        @Parameter(description = "유저 아이디")
        @PathVariable(name = "user_id", required = true) String user_id,
        @Parameter(description = "date(현재시간)", example = "2022-01-17T15:45:30")
        @PathVariable(name = "date") LocalDateTime date
){

    return orderService.userOrderRecordsByNow(user_id, date);
}
//    @GetMapping("restaurant_order_list_now")
//    public List<Order> restaurantOrderListNow(@RequestParam Map<String, Object> requestParam){
//        Long restaurant_id = (Long) requestParam.get("restaurant_id");
//        LocalDateTime date = (LocalDateTime) requestParam.get("date");
//        return orderService.restaurantOrderRecordsByNow(restaurant_id, date);
//    }

@RequestMapping(value = "restaurant_order_list_now/{restaurant_id}/{date}", method = RequestMethod.GET)
public List<Order> restaurantOrderListNow(
        @Parameter(description = "식당 아이디")
        @PathVariable(name = "restaurant_id") Long restaurant_id,
        @Parameter(description = "date(현재시간)", example = "2022-01-17T15:45:30")
        @PathVariable(name = "date") LocalDateTime date
){
    return orderService.restaurantOrderRecordsByNow(restaurant_id, date);
}

}
