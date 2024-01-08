package com.example.micromap.repository;

import com.example.micromap.domain.Order;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    //주문하기 - 가게, 메뉴, 결제 모두 선택시 가능
    Long createOrder(Order order);

    //주문 상태 업데이트
    String acceptOrder(Long order_id, Long restaurant_id, boolean is_accepted); // 주문 수락(Accept)
    String finishOrder(Long order_id, Long restaurant_id, boolean is_finished); //주문 완료(Finish)
    String takenOrder(Long order_id, Long restaurant_id, boolean is_taken); //주문 픽업(taken)

    //주문 기록 조회
    List<Order> selectOrderRecordsOfUser(String user_id); //유저 주문 기록 조회(All)
   List<Order> selectOrderRecordsOfRestaurant(Long restaurant_id); //가게 주문 기록 조회(All)
    List<Order> selectOrderRecordsOfUserByDate(String user_id, LocalDateTime date); //특정 날짜 유저 주문 기록
    List<Order> selectOrderRecordsOfRestaurantByDate(Long restaurant_id, LocalDateTime date); //특정 날짜 가게 주문 기록
    List<Order> selectOrderListOfUserByNow(String user_id, LocalDateTime date); // 지금 진행중인 주문 조회 - 유저
    List<Order> selectOrderListOfRestaurantByNow(Long restaurant_id, LocalDateTime date); //지금 진행중인 주문 조회 - 가게

}
