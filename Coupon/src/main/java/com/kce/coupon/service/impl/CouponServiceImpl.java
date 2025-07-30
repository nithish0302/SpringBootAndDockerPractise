package com.kce.coupon.service.impl;

import com.kce.coupon.entity.Coupon;
import com.kce.coupon.repository.CouponRepository;
import com.kce.coupon.service.CouponService;
import com.kce.coupon.util.NoCouponFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {

    CouponRepository couponRepository;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public List<Coupon> findall() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon findById(long id) {
        Optional<Coupon> existing = couponRepository.findById(id);
        if (existing.isPresent()) {
            return existing.get();
        }
        else {
            throw new NoCouponFoundException("coupon not found");
        }
    }

    @Override
    public Coupon findByCouponCode(String couponCode) {
        Optional<Coupon> existing = couponRepository.findByCouponCode(couponCode);
        if (existing.isPresent()) {
            return existing.get();
        }
        else {
            throw new NoCouponFoundException("coupon not found");
        }
    }

    @Override
    public Coupon add(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon updateById(Long id, Coupon coupon) {
        Optional<Coupon> isexisting = couponRepository.findById(id);
        if (isexisting.isPresent()) {
            Coupon existing = isexisting.get();
            existing.setCouponCode(coupon.getCouponCode());
            existing.setDiscount(coupon.getDiscount());
           return couponRepository.save(existing);
        }
        else {
            throw new NoCouponFoundException("coupon not found");
    }}

    @Override
    public Coupon deleteById(Long id) {
        Optional<Coupon> existing = couponRepository.findById(id);
        if (existing.isPresent()) {
            couponRepository.delete(existing.get());
            return existing.get();
        }
        else {
            throw new NoCouponFoundException("coupon not found");
        }
    }
}
