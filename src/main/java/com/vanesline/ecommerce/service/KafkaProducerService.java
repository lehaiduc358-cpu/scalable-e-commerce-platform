package com.vanesline.ecommerce.service;

public interface KafkaProducerService {
    void sendMessage(String topic, String message);
}
