package com.vanesline.ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "{validation.username.required}")
    @Size(min = 4, max = 50, message = "{validation.username.size}")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "{validation.username.invalid}")
    private String username;

    @NotBlank(message = "{validation.email.required}")
    @Email(message = "{validation.email.invalid}")
    private String email;

    @NotBlank(message = "{validation.password.required}")
    @Size(min = 6, max = 100, message = "{validation.password.size}")
    private String password;

    @NotBlank(message = "{validation.fullName.required}")
    @Size(max = 100, message = "{validation.fullName.size}")
    private String fullName;

    @NotBlank(message = "{validation.phone.required}")
    @Pattern(regexp = "^(0|\\+84)[0-9]{9}$", message = "{validation.phone.invalid}")
    private String phone;
}
