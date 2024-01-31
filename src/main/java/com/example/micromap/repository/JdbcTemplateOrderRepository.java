package com.example.micromap.repository;

import com.example.micromap.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateOrderRepository implements OrderRepository{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateOrderRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long createOrder(Order order) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("orders").usingGeneratedKeyColumns("order_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id",order.getUserId());
        parameters.put("restaurant_id", order.getRestaurantId());
        parameters.put("created_at", order.getCreatedAt());
        parameters.put("is_accepted", order.getAccepted());
        parameters.put("is_finished", order.getFinished());
        parameters.put("is_taken", order.getTaken());
        parameters.put("price", order.getPrice());
        parameters.put("is_pay", order.getPay());
        parameters.put("pay_info", order.getPayInfo());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        order.setOrderId(key.longValue());
        return key.longValue();
    }

    @Override
    public String acceptOrder(Long order_id, Long restaurant_id, boolean isAccepted) {
        String sql = "UPDATE orders SET is_accepted = ? WHERE order_id = ? AND restaurant_id = ?";
        jdbcTemplate.update(sql, isAccepted, order_id, restaurant_id);
        return "accept";
    }

    @Override
    public String finishOrder(Long order_id, Long restaurant_id, boolean isFinished) {
        String sql = "UPDATE orders SET is_finished = ? WHERE order_id = ? AND restaurant_id = ?";
        jdbcTemplate.update(sql, isFinished, order_id, restaurant_id);
        return "finish";
    }

    @Override
    public String takenOrder(Long order_id, Long restaurant_id, boolean isTaken) {
        String sql = "UPDATE orders SET is_taken = ? WHERE order_id = ? AND restaurant_id = ?";
        jdbcTemplate.update(sql, isTaken, order_id, restaurant_id);
        return "taken";
    }

    @Override
    public List<Order> selectOrderRecordsOfUser(String user_id) {
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        List<Order> result = jdbcTemplate.query(sql, orderRowMapper(), user_id);
        return result;
    }

    @Override
    public List<Order> selectOrderRecordsOfRestaurant(Long restaurant_id) {
        String sql = "SELECT * FROM orders WHERE restaurant_id = ?";
        List<Order> result = jdbcTemplate.query(sql, orderRowMapper(), restaurant_id);
        return result;
    }

    @Override
    public List<Order> selectOrderRecordsOfUserByDate(String user_id, LocalDateTime date) {
        String sql = "SELECT * FROM orders WHERE user_id = ? AND created_at = ?";
        List<Order> result = jdbcTemplate.query(sql, orderRowMapper(), user_id, date);
        return result;
    }

    @Override
    public List<Order> selectOrderRecordsOfRestaurantByDate(Long restaurant_id, LocalDateTime date) {
        String sql = "SELECT * FROM orders WHERE restaurant_id = ? AND created_at = ?";
        List<Order> result = jdbcTemplate.query(sql, orderRowMapper(), restaurant_id, date);
        return result;
    }

    @Override
    public List<Order> selectOrderListOfUserByNow(String user_id, LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = date.format(formatter);
        String nextDateString = date.plusDays(1).format(formatter);
        String sql = "SELECT * FROM orders WHERE user_id = ? AND created_at >= '"+dateString+" 00:00:00' AND created_at < '" +nextDateString + "' AND (is_accepted = false OR is_finished = false OR is_taken = false)";
        List<Order> result = jdbcTemplate.query(sql, orderRowMapper(), user_id);
        System.out.println(sql);
        System.out.println(dateString);
        System.out.println(nextDateString);
        return result;
    }

    @Override
    public List<Order> selectOrderListOfRestaurantByNow(Long restaurant_id, LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = date.format(formatter);
        String nextDateString = date.plusDays(1).format(formatter);
        String sql = "SELECT * FROM orders WHERE restaurant_id = ? AND created_at >= '"+dateString+" 00:00:00' AND created_at < '" +nextDateString + "' AND (is_accepted = false OR is_finished = false OR is_taken = false)";
        List<Order> result = jdbcTemplate.query(sql, orderRowMapper(), restaurant_id);
        return result;
    }

    public String deleteOrder(Long order_id){
        String sql = "DELETE orders WHERE order_id = ?";
        jdbcTemplate.update(sql, order_id);
        return "주문이 삭제되었습니다.";
    }

    //Delete order

    private RowMapper<Order> orderRowMapper(){
        return (rs, rowNum) -> {
          Order order = new Order();
          order.setOrderId(rs.getLong("order_id"));
          order.setUserId(rs.getString("user_id"));
          order.setRestaurantId(rs.getLong("restaurant_id"));
          LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
          order.setCreatedAt(created_at);
          order.setAccepted(rs.getBoolean("is_accepted"));
          order.setFinished(rs.getBoolean("is_finished"));
          order.setTaken(rs.getBoolean("is_taken"));
          order.setPrice(rs.getDouble("price"));
          order.setPay(rs.getBoolean("is_pay"));
          order.setPayInfo(rs.getString("pay_info"));
          return order;
        };
    }
}
