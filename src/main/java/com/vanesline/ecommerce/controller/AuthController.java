package com.vanesline.ecommerce.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanesline.ecommerce.dto.request.LoginRequest;
import com.vanesline.ecommerce.dto.request.RegisterRequest;
import com.vanesline.ecommerce.dto.response.AuthResponse;
import com.vanesline.ecommerce.dto.response.RegisterResponse;
import com.vanesline.ecommerce.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MessageSource messageSource;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request, Locale locale) {
        RegisterResponse response = authService.register(request);
        response.setMessage(
                messageSource.getMessage("auth.register.success", null, locale));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
