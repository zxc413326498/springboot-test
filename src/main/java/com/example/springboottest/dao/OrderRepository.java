package com.example.springboottest.dao;

import com.example.springboottest.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByZip(String zip);
    //需要查询在给定日期范围内投递给指定邮政编码的所有订单
    List<Order> readOrdersByZipAndPlacedAtBetween(String zip, Date startDate, Date endDate);
    //可以随意将方法命名为任何想要的名称，并使用 @Query 对其进行注解，以显式地指定调用方法时要执行的查询
//    @Query(" Order o where o.deliveryCity='Seattle'")//关键词order报错
//    List<Order> readOrdersDeliveredInSeattle();
}