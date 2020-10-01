package com.misiak.autoexpense.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private String message;
    private int status;
    private Timestamp timestamp;
}
