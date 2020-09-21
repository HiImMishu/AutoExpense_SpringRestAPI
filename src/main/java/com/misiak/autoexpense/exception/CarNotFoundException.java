package com.misiak.autoexpense.exception;

import lombok.Getter;

@Getter
public class CarNotFoundException extends RuntimeException {
    private String id;

    public CarNotFoundException(String id) {
        this.id = id;
    }
}
