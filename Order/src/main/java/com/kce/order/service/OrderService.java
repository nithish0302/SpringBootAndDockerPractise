package com.kce.order.service;

import com.kce.order.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> findall();
    Order findById(Long id);
    Order findByOrderName(String name);
    Order add(Order order);
    Order updateById(Long id,Order order);
    Order deleteById(Long id);
    Order addWithCoupon(Order order);


}
