package com.example.authservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class UserDTO {
    private String username;
    private String fullName;
    private String email;
    private String address;
    private String phone;

    public UserDTO(String username, String fullName, String email, String address, String phone) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
}
