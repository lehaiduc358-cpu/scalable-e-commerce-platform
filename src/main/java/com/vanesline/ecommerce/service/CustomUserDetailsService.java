package com.vanesline.ecommerce.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vanesline.ecommerce.entity.Role;
import com.vanesline.ecommerce.entity.User;
import com.vanesline.ecommerce.exception.BusinessException;
import com.vanesline.ecommerce.exception.ErrorCode;
import com.vanesline.ecommerce.repository.RoleRepository;
import com.vanesline.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Role role = roleRepository.findById(user.getRoleId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority(role.getName()))
                .build();
    }
}
