package com.leonel.pruebarule1.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Rule1ExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Rule1Exception.class)
    public ResponseEntity<?> handle(Rule1Exception rule1Exception){
        return new ResponseEntity<>(rule1Exception.getMessage(), rule1Exception.getHttpStatus());
    }
}
