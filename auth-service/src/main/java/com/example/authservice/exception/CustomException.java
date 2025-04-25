package com.example.authservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends RuntimeException {
    private int code;
    private String message;

    public CustomException(HttpStatus code, String message) {
        this.code = code.value();
        this.message = message;
    }
}
