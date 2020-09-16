package com.misiak.autoexpense.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class UserErrorResponse {
    private String message;
    private int status;
    private Timestamp timestamp;
}
