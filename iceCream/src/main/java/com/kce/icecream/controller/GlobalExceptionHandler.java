package com.kce.icecream.controller;


import com.kce.icecream.entity.ErrorDetail;
import com.kce.icecream.util.NoIceCreamFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception{


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllException(Exception ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(NoIceCreamFoundException.class)
    public ResponseEntity<?> handleNoIceCreamFoundException(NoIceCreamFoundException ex, WebRequest request) {
        ErrorDetail error= new  ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
