package com.example.micromap.repository;

import com.example.micromap.domain.Like;
import com.example.micromap.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateLikeRepository implements LikeRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateLikeRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

//    @Override
//    public Long increaseLike(Long restaurant_id) {
//        String increase_sql = "UPDATE likes SET number_of_likes = number_of_likes + 1 WHERE restaurant_id = ?";
//        jdbcTemplate.update(increase_sql, restaurant_id);
//
//        String select_sql = "SELECT number_of_likes FROM likes WHERE restaurant_id = ?";
//        Long number_of_likes = jdbcTemplate.queryForObject(select_sql, Long.class, restaurant_id);
//        return number_of_likes;
//    }
@Override
public Long increaseLike(Long restaurant_id) {
    String increase_sql = "UPDATE likes SET number_of_likes = number_of_likes + 1 WHERE restaurant_id = ?";
    jdbcTemplate.update(increase_sql, restaurant_id);

    String select_sql = "SELECT number_of_likes FROM likes WHERE restaurant_id = ?";

    // Use query method instead of queryForObject
    List<Long> result = jdbcTemplate.query(select_sql, (rs, rowNum) -> rs.getLong("number_of_likes"), restaurant_id);

    // Check if the result list is empty
    if (result.isEmpty()) {
        // Handle the case where no result is found
        return null; // or return a default value
    } else {
        // Return the first (and only) element from the result list
        return result.get(0);
    }
}

    @Override
    public Long decreaseLike(Long restaurant_id) {
        String decrease_sql = "UPDATE likes SET number_of_likes = number_of_likes - 1 WHERE restaurant_id = ?";
        jdbcTemplate.update(decrease_sql, restaurant_id);

        String select_sql = "SELECT number_of_likes FROM likes WHERE restaurant_id = ?";

        List<Long> result = jdbcTemplate.query(select_sql, (rs, rowNum) -> rs.getLong("number_of_likes"), restaurant_id);

        // Check if the result list is empty
        if (result.isEmpty()) {
            // Handle the case where no result is found
            return null; // or return a default value
        } else {
            // Return the first (and only) element from the result list
            return result.get(0);
        }
    }

    @Override
    public Long selectRestaurantNumberOfLikes(Long restaurant_id) {
        String select_sql = "SELECT number_of_likes FROM likes WHERE restaurant_id = ?";
        List<Long> result = jdbcTemplate.query(select_sql, (rs, rowNum) -> rs.getLong("number_of_likes"), restaurant_id);

        // Check if the result list is empty
        if (result.isEmpty()) {
            // Handle the case where no result is found
            return null; // or return a default value
        } else {
            // Return the first (and only) element from the result list
            return result.get(0);
        }
    }

    @Override
    public List<String> selectRestaurantLikeUserList(Long restaurant_id) {
        String select_sql = "SELECT user_id FROM likes WHERE restaurant_id = ?";
        List<String> result = jdbcTemplate.query(select_sql, new StringRowMapper());
        return result;
    }

    @Override
    public List<Long> selectMyLikeRestaurantList(String user_id) {
        String select_sql = "SELECT restaurant_id FROM likes WHERE restaurant_id = ?";
        List<Long> result = jdbcTemplate.query(select_sql, new LongRowMapper());
        //just Restuarant_id 돌려줄 것인가? 아니면 restaurant 정보 줄 것인가?
        return result;
    }


    public class StringRowMapper implements RowMapper<String> {
        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException{
            return rs.getString(1);
        }
    }

    public class LongRowMapper implements RowMapper<Long>{
        @Override
        public Long mapRow(ResultSet rs, int rowNum) throws SQLException{
            return rs.getLong(1);
        }
    }

}
