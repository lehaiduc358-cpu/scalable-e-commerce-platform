package com.vanesline.ecommerce.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

        USER_NOT_FOUND(
                        HttpStatus.NOT_FOUND,
                        "error.user.not_found"),

        USERNAME_ALREADY_EXISTS(
                        HttpStatus.BAD_REQUEST,
                        "error.user.username_exists"),

        EMAIL_ALREADY_EXISTS(
                        HttpStatus.BAD_REQUEST,
                        "error.user.email_exists"),

        INVALID_CREDENTIALS(
                        HttpStatus.UNAUTHORIZED,
                        "error.auth.invalid_credentials"),

        ROLE_NOT_FOUND(
                        HttpStatus.NOT_FOUND,
                        "error.role.not_found"),
        VALIDATION_ERROR(
                        HttpStatus.BAD_REQUEST,
                        "error.validation");

        private final HttpStatus status;
        private final String messageKey;
}
