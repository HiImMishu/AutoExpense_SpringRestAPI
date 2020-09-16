package com.misiak.autoexpense.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private String id;

    public UserNotFoundException(String id) {
        this.id = id;
    }
}
