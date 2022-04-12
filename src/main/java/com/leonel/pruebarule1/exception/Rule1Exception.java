package com.leonel.pruebarule1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class Rule1Exception extends RuntimeException{

    private HttpStatus httpStatus;

    public Rule1Exception(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus= httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

