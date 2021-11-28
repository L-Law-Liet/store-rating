package com.example.storeratingservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "store_rating_test", groupId = "group_rating_id")
    public void consume(String text) {
        System.out.println("Commented: " + text);

    }



}