package com.example.micromap.controller;

import com.example.micromap.domain.Restaurant;
import com.example.micromap.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

//    @Operation(summary = "insert_restaurant")
//    @PostMapping("/insert_restaurant")
//    public Long insertRestaurant(@RequestBody Map<String, Object> requestData){
//        String restaurant_name = (String) requestData.get("restaurant_name");
//        String address = (String) requestData.get("address");
//        String tel = (String) requestData.get("tel");
//        double latitude = (double) requestData.get("latitude");
//        double longitude = (double) requestData.get("longitude");
//        Map<String, Integer> openHourMap = (Map<String, Integer>) requestData.get("open_hour");
//        int open_hour = openHourMap.get("hour");
//        int open_minute = openHourMap.get("minute");
//
//        Map<String, Integer> closeHourMap = (Map<String, Integer>) requestData.get("close_hour");
//        int close_hour = closeHourMap.get("hour");
//        int close_minute = closeHourMap.get("minute");
//        int status_id = (int) requestData.get("status_id");
//
//        Restaurant restaurant = new Restaurant();
//        restaurant.setRestaurantName(restaurant_name);
//        restaurant.setAddress(address);
//        restaurant.setTel(tel);
//        restaurant.setLatitude(latitude);
//        restaurant.setLongitude(longitude);
//        restaurant.setOpenHour(LocalTime.of(open_hour, open_minute));
//        restaurant.setCloseHour(LocalTime.of(close_hour, close_minute));
//        restaurant.setStatusId(status_id);
//
//        return restaurantService.registerRestaurant(restaurant);
//    }

@RequestMapping(value = "/insert_restaurant/{restaurant_name}/{address}/{tel}/{latitude}/{longitude}/{open_hour}/{open_minute}/{close_hour}/{close_minute}/{status_id}", method = RequestMethod.POST)
public Long insertRestaurant(
        @Parameter(description = "식당 이름")
        @PathVariable(name = "restaurant_name", required = true) String restaurant_name,
        @Parameter(description = "주소")
        @PathVariable(name = "address", required = true) String address,
        @Parameter(description = "전화번호")
        @PathVariable(name = "tel", required = true) String tel,
        @Parameter(description = "위도")
        @PathVariable(name = "latitude", required = true) double latitude,
        @Parameter(description = "경도")
        @PathVariable(name = "longitude", required = true) double longitude,
        @Parameter(description = "open_hour")
        @PathVariable(name = "open_hour", required = true) int open_hour,
        @Parameter(description = "open_minute")
        @PathVariable(name = "open_minute", required = true) int open_minute,
        @Parameter(description = "close_hour")
        @PathVariable(name = "close_hour", required = true) int close_hour,
        @Parameter(description = "close_minute")
        @PathVariable(name = "close_minute", required = true) int close_minute,
        @Parameter(description = "상태", example = "1")
        @PathVariable(name = "status_id", required = true) int status_id

){

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
//    @GetMapping("select_restaurant_by_id")
//    public Optional<Restaurant> selectRestaurantById(@RequestParam(name = "restaurant_id") String restaurant_id){
//        //두 방식 다 성공
//        //@RequestParam Map<String, Object> requestParam
//        //Long restaurant_id = (Long) Long.parseLong((String) requestParam.get("restaurant_id"));
//        return restaurantService.selectRestaurantById(Long.parseLong(restaurant_id));
//    }
@RequestMapping(value = "select_restaurant_id/{restaurant_id}", method = RequestMethod.GET)
public Optional<Restaurant> selectRestaurantById(
    @Parameter(description="식당 아이디")
    @PathVariable(name="restaurant_id", required = true) String restaurant_id){
    return restaurantService.selectRestaurantById(Long.parseLong(restaurant_id));
}

}
