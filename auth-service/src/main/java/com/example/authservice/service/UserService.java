package com.example.authservice.service;

import com.example.authservice.dto.UserDTO;
import com.example.authservice.dto.response.ApiResponse;
import com.example.authservice.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    ApiResponse changeFullNameOrEmailOrAddress(UserDTO userDTO);
}
