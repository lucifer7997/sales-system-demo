package com.example.authservice.service;

import com.example.authservice.dto.request.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest registerRequest);
}
