package com.example.micromap.controller;

import com.example.micromap.domain.Restaurant;
import com.example.micromap.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;

@Tag(name = "restaurant_API", description = "Swagger Test API For Restaurant")
//@RequestMapping("/restaurant")
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Operation(summary = "insert_restaurant")
    @PostMapping("/insert_restaurant")
    public Long insertRestaurant(@RequestBody Map<String, Object> requestData){
        String restaurant_name = (String) requestData.get("restaurant_name");
        String address = (String) requestData.get("address");
        String tel = (String) requestData.get("tel");
        double latitude = (double) requestData.get("latitude");
        double longitude = (double) requestData.get("longitude");
        Map<String, Integer> openHourMap = (Map<String, Integer>) requestData.get("open_hour");
        int open_hour = openHourMap.get("hour");
        int open_minute = openHourMap.get("minute");

        Map<String, Integer> closeHourMap = (Map<String, Integer>) requestData.get("close_hour");
        int close_hour = closeHourMap.get("hour");
        int close_minute = closeHourMap.get("minute");
        int status_id = (int) requestData.get("status_id");

        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurant_name);
        restaurant.setAddress(address);
        restaurant.setTel(tel);
        restaurant.setLatitude(latitude);
        restaurant.setLongitude(longitude);
        restaurant.setOpenHour(LocalTime.of(open_hour, open_minute));
        restaurant.setCloseHour(LocalTime.of(close_hour, close_minute));
        restaurant.setStatusId(status_id);

        return restaurantService.registerRestaurant(restaurant);
    }

    @PostMapping("/update_restaurant")
    public Restaurant updateRestaurant(@RequestBody Map<String, Object> requestData){
        String restaurant_name = (String) requestData.get("restaurant_name");
        String address = (String) requestData.get("address");
        String tel = (String) requestData.get("tel");
        double latitude = (double) requestData.get("latitude");
        double longitude = (double) requestData.get("longitude");
        Map<String, Integer> openHourMap = (Map<String, Integer>) requestData.get("open_hour");
        int open_hour = openHourMap.get("hour");
        int open_minute = openHourMap.get("minute");

        Map<String, Integer> closeHourMap = (Map<String, Integer>) requestData.get("close_hour");
        int close_hour = closeHourMap.get("hour");
        int close_minute = closeHourMap.get("minute");
        int status_id = (int) requestData.get("status_id");

        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurant_name);
        restaurant.setAddress(address);
        restaurant.setTel(tel);
        restaurant.setLatitude(latitude);
        restaurant.setLongitude(longitude);
        restaurant.setOpenHour(LocalTime.of(open_hour, open_minute));
        restaurant.setCloseHour(LocalTime.of(close_hour, close_minute));
        restaurant.setStatusId(status_id);

        return restaurantService.updateRestaurant(restaurant);
    }
    @GetMapping("select_restaurant_by_id")
    public Optional<Restaurant> selectRestaurantById(@RequestParam(name = "restaurant_id") String restaurant_id){
        //두 방식 다 성공
        //@RequestParam Map<String, Object> requestParam
        //Long restaurant_id = (Long) Long.parseLong((String) requestParam.get("restaurant_id"));
        return restaurantService.selectRestaurantById(Long.parseLong(restaurant_id));
    }

}
