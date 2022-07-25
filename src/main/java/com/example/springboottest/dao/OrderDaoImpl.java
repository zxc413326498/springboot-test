package com.example.springboottest.dao;

import com.example.springboottest.data.Order;
import com.example.springboottest.data.Taco;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public OrderDaoImpl(JdbcTemplate jdbc) {
        this.orderInserter=new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");

        this.orderTacoInserter=new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order_Tacos");

        this.objectMapper=new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId=saveOrderDetails(order);
        order.setId(orderId);

        List<Taco> tacoList=order.getTacos();
        for(Taco taco:tacoList){
            saveTacoToOrder(taco,orderId);
        }
        return order;
    }

    private long saveOrderDetails(Order order) {
        @SuppressWarnings("unchecked")
        Map<String,Object> values=objectMapper.convertValue(order,Map.class);
        values.put("placedAt",order.getPlacedAt());

        return orderInserter.executeAndReturnKey(values).longValue();
    }

    private void saveTacoToOrder(Taco taco, long orderId) {
        Map<String,Object> values=new HashMap<>();
        values.put("tacoOrder",orderId);
        values.put("taco",taco.getId());

        orderTacoInserter.execute(values);
    }
}