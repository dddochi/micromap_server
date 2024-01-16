package com.example.micromap.service;

import com.example.micromap.repository.LikeRepository;

import java.util.List;

public class LikeService {
    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Long increaseLike(Long restaurant_id){
        return likeRepository.increaseLike(restaurant_id);
    }

    public Long decreaseLike(Long restaurant_id){
        return likeRepository.decreaseLike(restaurant_id);
    }

    public Long selectLike(Long restaurant_id){
        return likeRepository.selectRestaurantNumberOfLikes(restaurant_id);
    }

    public List<String> selectUserListWhoLikeRestaurant(Long restaurant_id){
        return likeRepository.selectRestaurantLikeUserList(restaurant_id);
    }

    public List<Long> selectRestaurantListThatUserLikes(String user_id){
        return likeRepository.selectMyLikeRestaurantList(user_id);
    }
}
