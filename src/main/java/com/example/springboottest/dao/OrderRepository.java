package com.example.springboottest.dao;

import com.example.springboottest.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByZip(String zip);
    //需要查询在给定日期范围内投递给指定邮政编码的所有订单
    List<Order> readOrdersByZipAndPlacedAtBetween(String zip, Date startDate, Date endDate);
}