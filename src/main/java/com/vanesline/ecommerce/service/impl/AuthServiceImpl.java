package com.vanesline.ecommerce.service.impl;

import java.time.Instant;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vanesline.ecommerce.constant.RoleConstants;
import com.vanesline.ecommerce.dto.request.LoginRequest;
import com.vanesline.ecommerce.dto.request.RegisterRequest;
import com.vanesline.ecommerce.dto.response.AuthResponse;
import com.vanesline.ecommerce.dto.response.RegisterResponse;
import com.vanesline.ecommerce.entity.Role;
import com.vanesline.ecommerce.entity.User;
import com.vanesline.ecommerce.exception.BusinessException;
import com.vanesline.ecommerce.exception.ErrorCode;
import com.vanesline.ecommerce.repository.RoleRepository;
import com.vanesline.ecommerce.repository.UserRepository;
import com.vanesline.ecommerce.service.AuthService;
import com.vanesline.ecommerce.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);
            return new AuthResponse(token, "Bearer");
        } catch (BadCredentialsException e) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
        }
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        Role role = roleRepository.findByName(RoleConstants.ROLE_USER)
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND));

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName().trim())
                .phone(request.getPhone())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .enabled(true)
                .roleId(role.getId())
                .build();

        User savedUser = userRepository.save(user);
        return RegisterResponse.builder()
                .userId(savedUser.getId())
                .username(savedUser.getUsername())
                .build();
    }

}
