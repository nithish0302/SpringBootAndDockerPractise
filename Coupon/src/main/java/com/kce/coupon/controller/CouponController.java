package com.kce.coupon.controller;


import com.kce.coupon.entity.Coupon;
import com.kce.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    CouponService couponService;


    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Coupon>> getAll(){
        return new ResponseEntity<>(couponService.findall(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Coupon> getById(@PathVariable Long id) {
        return   new ResponseEntity<>(couponService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/get/coupon/{couponCode}")
    public ResponseEntity<Coupon> getByCoupon(@PathVariable String couponCode) {
        return ResponseEntity.ok(couponService.findByCouponCode(couponCode));
    }
    @PostMapping("/add")
    public ResponseEntity<Coupon> add(@RequestBody Coupon coupon) {
        return  new  ResponseEntity<>(couponService.add(coupon), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Coupon> update(@PathVariable Long id, @RequestBody Coupon coupon) {
        return  new ResponseEntity<>(couponService.updateById(id, coupon), HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Coupon> delete(@RequestBody Coupon coupon) {
        return   new ResponseEntity<>(couponService.deleteById(coupon.getId()), HttpStatus.OK);
    }
}
