package com.vanesline.ecommerce.service;

public interface EmailService {
    void sendSimpleEmail(String toEmail, String subject, String body);
}
