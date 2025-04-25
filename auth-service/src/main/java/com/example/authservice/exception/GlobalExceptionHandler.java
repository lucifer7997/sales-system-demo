package com.example.authservice.exception;

import com.example.authservice.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(CustomException e) {
        ApiResponse apiResponse = new ApiResponse(e.getCode(), e.getMessage(), null);
        return ResponseEntity.status(e.getCode()).body(apiResponse);
    }
}
