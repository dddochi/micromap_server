package com.example.micromap;

import com.example.micromap.repository.*;
import com.example.micromap.service.MemberService;
import com.example.micromap.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private  DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Bean
    public MemberService memberService(){

        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new JdbcTemplateMemberRepository(dataSource);
    }

    @Bean
    public RestaurantService restaurantService(){
        return new RestaurantService(restaurantRepository());
    }

    @Bean
    public RestaurantRepository restaurantRepository(){
        return new JdbcTemplateRestaurantRepository(dataSource);
    }

    @Bean
    public OrderRepository orderRepository(){
        return new JdbcTemplateOrderRepository(dataSource);
    }
}
