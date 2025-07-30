package com.kce.order.feignclient;

import com.kce.order.model.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COUPON-SERVICE")
public interface CouponFeign {

    @GetMapping("/api/coupon/get/coupon/{couponCode}")
    public ResponseEntity<Coupon> getByCoupon(@PathVariable String couponCode);
}
