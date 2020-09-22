package com.misiak.autoexpense.exception;

import lombok.Getter;

@Getter
public class EngineNotFoundException extends RuntimeException {
    private String message;

    public EngineNotFoundException(String s) {
        this.message = s;
    }
}
