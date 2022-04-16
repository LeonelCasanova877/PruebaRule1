package com.leonel.pruebarule1.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.leonel.pruebarule1.dto.ErrorDTO;
import com.leonel.pruebarule1.dto.ErrorDetailsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class Rule1ExceptionHandler{

    @ExceptionHandler(Rule1Exception.class)
    public ResponseEntity<ErrorDTO> handle(Rule1Exception rule1Exception){

        return new ResponseEntity(rule1Exception.getErrorDTO(),rule1Exception.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<ErrorDetailsDTO> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            Object rejectedValue= ((FieldError) error).getRejectedValue();
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new ErrorDetailsDTO(rejectedValue, fieldName, errorMessage));
        });
        ErrorDTO errorDTO = new ErrorDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Validation error", errors);
        return new ResponseEntity(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
    protected ResponseEntity<ErrorDTO> handleFormatExceptions(InvalidFormatException ex) {

        ErrorDTO errorDTO = new ErrorDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Format of "+ ex.getValue().toString()+" is not valid, the format is yyyy-MM-dd HH:mm:ss");
        return new ResponseEntity(errorDTO, HttpStatus.BAD_REQUEST);
    }


}
