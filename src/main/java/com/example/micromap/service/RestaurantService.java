package com.example.micromap.service;

import com.example.micromap.domain.Restaurant;
import com.example.micromap.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RestaurantService {
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Long registerRestaurant(Restaurant restaurant){
        return restaurantRepository.insertRestaurant(restaurant);
    }

    public Restaurant updateRestaurant(Restaurant restaurant){
        return restaurantRepository.updateRestaurant(restaurant);
    }

    public Optional<Restaurant> selectRestaurantById(Long restaurant_id){
        return restaurantRepository.selectRestaurantById(restaurant_id);
    }
}
