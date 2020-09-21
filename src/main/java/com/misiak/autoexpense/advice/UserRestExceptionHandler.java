package com.misiak.autoexpense.advice;

import com.misiak.autoexpense.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class UserRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse("User not found with id: " + exception.getId(), HttpStatus.NOT_FOUND.value(), new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
