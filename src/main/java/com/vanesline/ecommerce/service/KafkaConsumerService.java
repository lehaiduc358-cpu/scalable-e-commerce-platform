package com.vanesline.ecommerce.service;

public interface KafkaConsumerService {
    void consume(String message);
}
