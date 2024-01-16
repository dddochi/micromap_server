package com.example.micromap.repository;

import com.example.micromap.domain.Like;
import com.example.micromap.domain.Restaurant;

import java.util.List;

public interface LikeRepository {
    Long increaseLike(Long restaurant_id);
    Long decreaseLike(Long restaurant_id);

    Long selectRestaurantNumberOfLikes(Long restaurant_id);

    List<String> selectRestaurantLikeUserList(Long restaurant_id);

    List<Long> selectMyLikeRestaurantList(String user_id);
}
