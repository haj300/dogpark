package com.se.pvt3.dogpark.web.controller;

import com.se.pvt3.dogpark.services.DogParkNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e){
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List> handleBindException(BindException ex){
        return new ResponseEntity(ex.getAllErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DogParkNotFoundException.class)
    public ResponseEntity<List> handleDogParkNotFoundException(){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
