package com.vanesline.ecommerce.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.vanesline.ecommerce.service.KafkaConsumerService;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @Override
    @KafkaListener(topics = "my-topic", groupId = "ecommerce-consumer-group")
    public void consume(String message) {
        System.out.println("Đã nhận tin nhắn: " + message);
    }
}
