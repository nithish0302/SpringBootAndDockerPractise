package com.kce.order.service.impl;

import com.kce.order.entity.Order;
import com.kce.order.feignclient.CouponFeign;
import com.kce.order.feignclient.IceCreamFeign;
import com.kce.order.model.Coupon;
import com.kce.order.model.IceCream;
import com.kce.order.repository.OrderRepository;
import com.kce.order.service.OrderService;
import com.kce.order.util.NoOrderFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;
    private CouponFeign couponFeign;
    IceCreamFeign iceCreamFeign;
    public OrderServiceImpl(OrderRepository orderRepository, IceCreamFeign iceCreamFeign,CouponFeign couponFeign) {
        this.orderRepository = orderRepository;
        this.iceCreamFeign = iceCreamFeign;
        this.couponFeign = couponFeign;
    }

    @Override
    public List<Order> findall() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        }
        else {
            throw new NoOrderFoundException("No order found ");
        }
    }

    @Override
    public Order findByOrderName(String name) {
        Optional<Order> order = orderRepository.findByName(name);
        if (order.isPresent()) {
            return order.get();
        }
        else {
            throw new NoOrderFoundException("No order found ");
        }
    }

    @Override
    public Order add(Order order) {
        IceCream iceCream = iceCreamFeign.findByName(order.getIceCream()).getBody();
        order.setPrice(order.getQuantity()*iceCream.getPrice());
        return orderRepository.save(order);
    }

    @Override
    public Order updateById(Long id, Order order) {
        Optional<Order> isexisting = orderRepository.findById(id);
        if (isexisting.isPresent()) {
            Order existingOrder = isexisting.get();
            if (order.getName() != null) {
                existingOrder.setName(order.getName());
            }
            if (order.getCoupon() != null) {
                existingOrder.setCoupon(order.getCoupon());
            }
            if (order.getQuantity()!=existingOrder.getQuantity())
            {
                existingOrder.setQuantity(order.getQuantity());

            }
            if (order.getIceCream() != null && order.getIceCream()!=existingOrder.getIceCream()) {
                existingOrder.setIceCream(order.getIceCream());
            }
            return orderRepository.save(existingOrder);


        }
        else
        {
            throw new NoOrderFoundException("No order found ");
        }
    }

    @Override
    public Order deleteById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
            return  order.get();
        }
        else {
            throw new NoOrderFoundException("No order found ");
        }
    }

    @Override
    public Order addWithCoupon(Order order) {
        IceCream iceCream = iceCreamFeign.findByName(order.getIceCream()).getBody();
        Coupon coupon = couponFeign.getByCoupon(order.getCoupon()).getBody();

        if(iceCream != null && iceCream.getQuantity() >= order.getQuantity() && coupon != null){
            order.setPrice((iceCream.getPrice()*order.getQuantity())-coupon.getDiscount());
            return orderRepository.save(order);
        }
        else{
            throw new NoOrderFoundException("Not Clear data");
        }


    }
}
