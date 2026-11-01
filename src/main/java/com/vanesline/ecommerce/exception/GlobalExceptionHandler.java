package com.vanesline.ecommerce.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vanesline.ecommerce.dto.response.ErrorResponse;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex, Locale locale) {
        String message = messageSource.getMessage(ex.getErrorCode().getMessageKey(), null, locale);

        ErrorResponse response = ErrorResponse.builder()
                .code(ex.getErrorCode().name())
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(ex.getErrorCode().getStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, Locale locale) {
        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;

        ErrorResponse response = ErrorResponse.builder()
                .code(errorCode.name())
                .message(messageSource.getMessage(errorCode.getMessageKey(), null, locale))
                .details(errors)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }
}
