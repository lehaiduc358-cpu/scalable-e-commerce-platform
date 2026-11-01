package com.vanesline.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {

    private String accessToken;
    private String tokenType;
}
