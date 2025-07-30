package com.kce.order.controller;


import com.kce.order.entity.ErrorDetail;
import com.kce.order.util.NoOrderFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllException(Exception ex, WebRequest request){
        ErrorDetail error=new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoOrderFoundException.class)
    public ResponseEntity<?> handleNoOrderFoundException(NoOrderFoundException ex, WebRequest request){
        ErrorDetail error=new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
