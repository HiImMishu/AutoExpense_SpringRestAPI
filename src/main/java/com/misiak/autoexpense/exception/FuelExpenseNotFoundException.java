package com.misiak.autoexpense.exception;

import lombok.Getter;

@Getter
public class FuelExpenseNotFoundException extends RuntimeException {
    private String message;
    public FuelExpenseNotFoundException(String s) {
        this.message = s;
    }
}
