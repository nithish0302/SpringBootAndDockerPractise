package com.kce.coupon.util;

public class NoCouponFoundException extends RuntimeException {
    public NoCouponFoundException(String message) {
        super(message);
    }
}
