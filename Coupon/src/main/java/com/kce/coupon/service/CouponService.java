package com.kce.coupon.service;

import com.kce.coupon.entity.Coupon;

import java.util.List;

public interface CouponService {

    List<Coupon> findall();
    Coupon findById(long  id);
    Coupon findByCouponCode(String couponCode);
    Coupon add(Coupon coupon);
    Coupon updateById(Long id, Coupon coupon);
    Coupon deleteById(Long id);


}
