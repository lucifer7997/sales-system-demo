package com.example.authservice.service.impl;

import com.example.authservice.Utils.CommonUtil;
import com.example.authservice.dto.request.RegisterRequest;
import com.example.authservice.dto.response.ApiResponse;
import com.example.authservice.entity.User;
import com.example.authservice.exception.CustomException;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void register(RegisterRequest registerRequest) {
        try {
            if (userRepository.findByUsername(registerRequest.getUsername()) != null) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "Username is already in use");
            }

            if (userRepository.findByEmail(registerRequest.getEmail()) != null) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "Email is already in use");
            }

            if (!registerRequest.getEmail().matches(CommonUtil.regexEmail)) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "Invalid email");
            }

            User user = User.builder()
                    .username(registerRequest.getUsername())
                    .email(registerRequest.getEmail())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .address(registerRequest.getAddress())
                    .phone(registerRequest.getPhone())
                    .build();

            userRepository.save(user);
        } catch (CustomException e) {
            throw e;
        }
    }
}
