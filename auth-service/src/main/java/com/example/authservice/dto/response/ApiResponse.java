package com.example.authservice.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {
    private int code;
    private String message;
    private Object data;

    public ApiResponse(HttpStatus code, String message, Object data) {
        this.code = code.value();
        this.message = message;
        this.data = data;
    }

    public ApiResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(HttpStatus code, String message) {
        this.code = code.value();
        this.message = message;
    }
}
