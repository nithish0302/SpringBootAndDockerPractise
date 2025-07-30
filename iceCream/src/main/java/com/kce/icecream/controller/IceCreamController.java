package com.kce.icecream.controller;


import com.kce.icecream.entity.ErrorDetail;
import com.kce.icecream.entity.IceCream;
import com.kce.icecream.feignclient.CouponFeign;
import com.kce.icecream.model.Coupon;
import com.kce.icecream.service.IceCreamService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/icecream")
public class IceCreamController {

    IceCreamService iceCreamService;
    CouponFeign couponFeign;

    @Autowired
    public IceCreamController(IceCreamService iceCreamService, CouponFeign couponFeign) {

        this.iceCreamService = iceCreamService;
        this.couponFeign = couponFeign;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<IceCream>>findAll()
    {
        return  new ResponseEntity<>(iceCreamService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<IceCream>findById(@PathVariable Long id) {
        return new ResponseEntity<>(iceCreamService.findById(id),HttpStatus.OK);

    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity<IceCream>findByName(@PathVariable String name) {
        return new ResponseEntity<>(iceCreamService.findByName(name),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<IceCream> add(@RequestBody IceCream iceCream) {
        return ResponseEntity.ok(iceCreamService.add(iceCream));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<IceCream> updateById(@PathVariable long id , @RequestBody IceCream iceCream) {
        return  new ResponseEntity<>(iceCreamService.updateById(id,iceCream),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<IceCream> deleteById(@PathVariable long id) {
        return new ResponseEntity<>(iceCreamService.deleteById(id),HttpStatus.OK);
    }


    @CircuitBreaker(name="ICECREAM-SERVICE",fallbackMethod = "handelerror")
    @GetMapping("/getfinal/{id}/{couponCode}")
    public ResponseEntity<IceCream>getfinalprice(@PathVariable long id, @PathVariable String couponCode) {

        Coupon coupon =couponFeign.getCoupon(couponCode).getBody();
        IceCream iceCream=iceCreamService.findById(id);
        iceCream.setPrice((long) (iceCream.getPrice()-coupon.getDiscount()));
            return  new  ResponseEntity<>(iceCream,HttpStatus.OK);
    }
    public ResponseEntity<ErrorDetail> handleError(Exception ex) {
        ErrorDetail errorDetails = new ErrorDetail(new Date(), ex.getMessage(), ex.getMessage());
        return new ResponseEntity<ErrorDetail>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }





}
