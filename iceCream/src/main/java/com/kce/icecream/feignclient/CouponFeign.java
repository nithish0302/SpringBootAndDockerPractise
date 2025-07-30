package com.kce.icecream.feignclient;


import com.kce.icecream.model.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="COUPON-SERVICE")
public interface CouponFeign {

    @GetMapping("/api/coupon/get/coupon/{couponCode}")
    public ResponseEntity<Coupon> getCoupon(@PathVariable("couponCode") String couponCode);

}
