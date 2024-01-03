package com.example.micromap.repository;

import com.example.micromap.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {
    Long insertRestaurant(Restaurant restaurant);

    Restaurant updateRestaurant(Restaurant restaurant);

    Restaurant deleteRestaurant(Long id);

    //select restaurant By id
    Optional<Restaurant> selectRestaurantById(Long id);

    //select restaurant By name
    Restaurant selectRestaurantByName(String restaurantName);

    //select restaurant list
    List<Restaurant> selectRestaurantList(double latitude, double longitude);
}
