package com.example.experiment7.service;

import com.example.experiment7.dto.LoginRequest;
import com.example.experiment7.dto.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        List<String> roles = authentication.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .toList();

        String token = Base64.getEncoder()
            .encodeToString((request.getUsername() + ":" + request.getPassword()).getBytes(StandardCharsets.UTF_8));

        return new LoginResponse(
            "Login successful",
            authentication.getName(),
            roles,
            "Basic " + token
        );
    }
}
