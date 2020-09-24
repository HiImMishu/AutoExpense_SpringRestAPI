package com.misiak.autoexpense.advice;

import com.misiak.autoexpense.exception.FuelExpenseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class FuelExpenseExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(FuelExpenseNotFoundException exception) {
        ErrorResponse error = new ErrorResponse("FuelExpense not found with id - " + exception.getMessage(), HttpStatus.NOT_FOUND.value(), new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
