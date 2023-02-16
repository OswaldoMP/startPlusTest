package com.app.startplus.startplustest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorsManager {
    @ExceptionHandler(value = ApiError.class)
    ResponseEntity<BaseErrorResponse> apiError(ApiError apiError) {
        BaseErrorResponse baseErrorResponse = BaseErrorResponse.builder()
                .status(apiError.getStatus())
                .message(apiError.getMessage()).build();
        return new ResponseEntity<>(baseErrorResponse, HttpStatus.valueOf(baseErrorResponse.getStatus()));
    }
}
