package com.example.authservice.controller;

import com.example.authservice.dto.UserDTO;
import com.example.authservice.dto.response.ApiResponse;
import com.example.authservice.entity.User;
import com.example.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/getByUsername")
    public ResponseEntity<ApiResponse> getUser(String username) {
        UserDTO userDTO;
        try {
            Optional<User> user = userService.findByUsername(username);
            if (user.isPresent()) {
                userDTO = user.map(u -> new UserDTO(u.getUsername(), u.getEmail(), u.getFullName(), u.getAddress(), u.getPhone()))
                        .orElse(null);
                return ResponseEntity.ok().body(new ApiResponse(HttpStatus.OK, "User found", userDTO));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(HttpStatus.BAD_REQUEST, "User not found", null));
    }

    @PostMapping("/changeFullNameOrEmailOrAddress")
    public ResponseEntity<ApiResponse> changeFullNameOrEmailOrAddress(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.changeFullNameOrEmailOrAddress(userDTO));
    }
}
