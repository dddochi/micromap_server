package com.example.micromap.controller;

import com.example.micromap.service.LikeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Like API", description = "Swagger Test API For Like")
@RequestMapping("/")
@RestController
public class LikeController {
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/increase_like")
    public Long increaseLike(@RequestBody Map<String, Object> requestData){
        Long restaurant_id = (Long) requestData.get("restaurant_id");
        return likeService.increaseLike(restaurant_id);
    }

    @PostMapping("/decrease_like")
    public Long decreaseLike(@RequestBody Map<String, Object> requestData){
        Long restaurant_id = (Long) requestData.get("restaurant_id");
        return likeService.decreaseLike(restaurant_id);
    }

    @GetMapping("select_like_number")
    public Long selectLikeNumber(@RequestParam Map<String, Object> requestParam){
        Long restaurant_id = (Long) requestParam.get("restaurant_id");
        return likeService.selectLike(restaurant_id);
    }

    @GetMapping("select_user_list")
    public List<String> selectUserList(@RequestParam Map<String, Object> requestParam){
        Long restaurant_id = (Long) requestParam.get("restaurant_id");
        return  likeService.selectUserListWhoLikeRestaurant(restaurant_id);
    }

    @GetMapping("select_restaurant_list")
    public  List<Long> selectRestaurant_list(@RequestParam Map<String, Object> requestParam){
        String user_id = (String) requestParam.get("user_id");
        return likeService.selectRestaurantListThatUserLikes(user_id);
    }


}
