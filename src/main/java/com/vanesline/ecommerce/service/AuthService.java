package com.vanesline.ecommerce.service;

import com.vanesline.ecommerce.dto.request.LoginRequest;
import com.vanesline.ecommerce.dto.request.RegisterRequest;
import com.vanesline.ecommerce.dto.response.AuthResponse;
import com.vanesline.ecommerce.dto.response.RegisterResponse;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    RegisterResponse register(RegisterRequest request);
}
