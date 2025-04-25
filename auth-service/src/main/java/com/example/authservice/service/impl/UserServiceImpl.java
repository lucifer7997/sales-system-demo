package com.example.authservice.service.impl;

import com.example.authservice.dto.UserDTO;
import com.example.authservice.dto.response.ApiResponse;
import com.example.authservice.entity.User;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        User user = new User();
        try {
            user = userRepository.findByUsername(username).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public ApiResponse changeFullNameOrEmailOrAddress(UserDTO userDTO) {
        try {
            User user = userRepository.findByUsername(userDTO.getUsername()).orElse(null);
            if (user != null) {
                user.setFullName(userDTO.getFullName());
                if (userRepository.findByEmail(userDTO.getEmail()) != null && !user.getEmail().equals(userDTO.getEmail())) {
                    return new ApiResponse(HttpStatus.BAD_REQUEST, "Email already exists", null);
                }
                user.setEmail(userDTO.getEmail());
                user.setAddress(userDTO.getAddress());
                userRepository.save(user);
                return new ApiResponse(HttpStatus.OK, "User updated", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse(HttpStatus.BAD_REQUEST, "User not found", null);
    }

    public UserDTO getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDTO userDTO = new UserDTO();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            userDTO = UserDTO.builder()
                    .username(user.getUsername())
                    .fullName(user.getFullName())
                    .phone(user.getPhone())
                    .address(user.getAddress())
                    .email(user.getEmail())
                    .build();
        }
        return userDTO;
    }
}
