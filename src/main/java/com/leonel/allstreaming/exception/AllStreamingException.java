package com.leonel.allstreaming.exception;

import com.leonel.allstreaming.dto.ErrorDTO;
import org.springframework.http.HttpStatus;


public class AllStreamingException extends RuntimeException{

    private ErrorDTO errorDTO;
    private HttpStatus httpStatus;

    public AllStreamingException(ErrorDTO errorDTO, HttpStatus httpStatus){
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

