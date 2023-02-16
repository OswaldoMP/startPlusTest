package com.app.startplus.startplustest.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BaseErrorResponse {
    private int status;
    private String message;
}
