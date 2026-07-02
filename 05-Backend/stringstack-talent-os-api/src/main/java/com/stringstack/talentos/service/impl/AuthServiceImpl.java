package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.auth.LoginRequest;
import com.stringstack.talentos.dto.auth.LoginResponse;
import com.stringstack.talentos.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Override
    public LoginResponse login(LoginRequest request) {

        return LoginResponse.builder()
                .token("Dummy JWT Token")
                .tokenType("Bearer")
                .userId(1L)
                .firstName("Shankar")
                .lastName("Pagad")
                .email(request.getEmail())
                .role("ADMIN")
                .build();
    }
}