package com.example.micromap.repository;

import com.example.micromap.domain.Member;
import com.example.micromap.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.Time;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateRestaurantRepository implements RestaurantRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateRestaurantRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long insertRestaurant(Restaurant restaurant) {
        //String insert_sql = "INSERT INTO RESTAURANT (restaurant_id, restaurant_name, address, tel, latitude, longitude, opening_hours, status_id) VALUES (?,?,?,?,?,?,?,?)";

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("restaurant").usingGeneratedKeyColumns("restaurant_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("restaurant_name", restaurant.getRestaurantName());
        parameters.put("address", restaurant.getAddress());
        parameters.put("tel", restaurant.getTel());
        parameters.put("latitude", restaurant.getLatitude());
        parameters.put("longitude", restaurant.getLongitude());
        parameters.put("open_hour", restaurant.getOpenHour());
        parameters.put("close_hour", restaurant.getCloseHour());
        parameters.put("status_id", restaurant.getStatusId());

//        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//        member.setId(key.longValue());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        restaurant.setRestaurantId(key.longValue());
        return key.longValue();
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        String update_restaurant = "UPDATE restaurant SET restaurant_name = ?, address = ?, latitude = ?, longitude = ? tel = ?, open_hour = ?, close_hour = ?, status_id = ?  WHERE restaurant_id = ?";
        jdbcTemplate.update(update_restaurant, restaurant.getRestaurantId(), restaurant.getAddress(), restaurant.getLatitude(), restaurant.getLongitude(), restaurant.getTel(), restaurant.getOpenHour(), restaurant.getCloseHour(), restaurant.getStatusId());
        return restaurant;
    }

    @Override
    public Restaurant deleteRestaurant(Long id) {
        return null;
    }

    @Override
    public Optional<Restaurant> selectRestaurantById(Long restaurant_id) {
        String sql = "select * from restaurant where restaurant_id = ?";
        List<Restaurant> result = jdbcTemplate.query(sql, restaurantRowMapper(), restaurant_id);
        return result.stream().findAny();
    }

    @Override
    public Restaurant selectRestaurantByName(String restaurantName) {
        return null;
    }

    @Override
    public List<Restaurant> selectRestaurantList(double latitude, double longitude) {
        return null;
    }

    private RowMapper<Restaurant> restaurantRowMapper(){
        return (rs, rowNum) -> {
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(rs.getLong("restaurant_id"));
            restaurant.setRestaurantName(rs.getString("restaurant_name"));
            restaurant.setAddress(rs.getString("address"));
            restaurant.setLatitude(rs.getDouble("latitude"));
            restaurant.setLongitude(rs.getDouble("longitude"));
            restaurant.setTel(rs.getString("tel"));
            //get "open hour"
            Time open_hour = rs.getTime("open_hour");
            LocalTime open_hour_local = open_hour.toLocalTime();
            restaurant.setOpenHour(open_hour_local);
            //get "close hour"
            Time close_hour = rs.getTime("close_hour");
            LocalTime close_hour_local = close_hour.toLocalTime();
            restaurant.setCloseHour(close_hour_local);

            restaurant.setStatusId(rs.getInt("status_id"));
            return restaurant;
        };
    }
}
