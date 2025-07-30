package com.kce.order.controller;


import com.kce.order.entity.Order;
import com.kce.order.feignclient.IceCreamFeign;
import com.kce.order.model.IceCream;
import com.kce.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final IceCreamFeign iceCreamFeign;
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService, IceCreamFeign iceCreamFeign) {
        this.orderService = orderService;
        this.iceCreamFeign = iceCreamFeign;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Order>> findAll() {
        return  new ResponseEntity<>(orderService.findall(), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Order>findById(@PathVariable  Long id){
        return  new  ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/get/name/{orderName}")
    public ResponseEntity<Order>findByOrderName(@PathVariable String name){
        return  new ResponseEntity<>(orderService.findByOrderName(name), HttpStatus.OK);
    }

//    @PostMapping("/add")
//    public ResponseEntity<Order> add(@RequestBody  Order order){
//        IceCream iceCream = iceCreamFeign.findByName(order.getIceCream()).getBody();
//
//        if(iceCream != null &&  iceCream.getQuantity() > order.getQuantity()){
//            return  new ResponseEntity<>(orderService.add(order), HttpStatus.OK);
//        }
//        else{
//            return  new ResponseEntity<>(orderService.add(order), HttpStatus.NO_CONTENT);
//        }
//
//    }
@PostMapping("/add")
public ResponseEntity<?> add(@RequestBody Order order) {
    IceCream iceCream = null;
    try {
        iceCream = iceCreamFeign.findByName(order.getIceCream()).getBody();
    } catch (Exception e) {
        return new ResponseEntity<>("Ice cream not found in inventory", HttpStatus.NOT_FOUND);
    }

    if (iceCream != null && iceCream.getQuantity() >= order.getQuantity()) {
        return new ResponseEntity<>(orderService.add(order), HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Insufficient stock or ice cream not available", HttpStatus.BAD_REQUEST);
    }
}


    @PutMapping("/update/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order order){
            return  new ResponseEntity<>(orderService.updateById(id,order), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Order> delete(@PathVariable Long id){
        return  new ResponseEntity<>(orderService.deleteById(id), HttpStatus.OK);
    }

    @PostMapping("/add/coupon")
    public ResponseEntity<Order> addWithCoupon(@RequestBody Order order){
        if(order.getIceCream() != null){
            return new ResponseEntity<>(orderService.addWithCoupon(order), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(orderService.addWithCoupon(order), HttpStatus.CONFLICT);
        }
    }


}
