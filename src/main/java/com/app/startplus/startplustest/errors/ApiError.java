package com.app.startplus.startplustest.errors;

import lombok.Data;

@Data
public class ApiError extends RuntimeException {
    private int status;
    private String message;

    public ApiError(int status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
}
