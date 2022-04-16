package com.leonel.pruebarule1.exception;

import com.leonel.pruebarule1.dto.ErrorDTO;
import org.springframework.http.HttpStatus;


public class Rule1Exception extends RuntimeException{

    private ErrorDTO errorDTO;
    private HttpStatus httpStatus;

    public Rule1Exception(ErrorDTO errorDTO, HttpStatus httpStatus){
        this.errorDTO= errorDTO;
        this.httpStatus= httpStatus;
    }

    public ErrorDTO getErrorDTO() {
        return errorDTO;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

