package com.kce.coupon.controller;


import com.kce.coupon.entity.ErrorDetail;
import com.kce.coupon.util.NoCouponFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleAllException(Exception ex, WebRequest request) {
      ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
      return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoCouponFoundException.class)
    public ResponseEntity<?> handleNoCouponException(Exception ex, WebRequest request) {
      ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
      return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    }

