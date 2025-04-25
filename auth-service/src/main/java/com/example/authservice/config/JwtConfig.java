package com.example.authservice.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtConfig {
    private String secretKey;
    private long expiration;
    private long refreshExpiration;
}
