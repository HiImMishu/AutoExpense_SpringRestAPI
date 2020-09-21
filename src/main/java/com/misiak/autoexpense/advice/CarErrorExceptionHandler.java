package com.misiak.autoexpense.advice;

import com.misiak.autoexpense.exception.CarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class CarErrorExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(CarNotFoundException exception) {
        ErrorResponse error = new ErrorResponse("Car not found with id - " + exception.getId(), HttpStatus.NOT_FOUND.value(), new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
