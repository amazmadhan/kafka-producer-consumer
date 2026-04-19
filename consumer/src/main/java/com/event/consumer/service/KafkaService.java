package com.event.consumer.service;

import com.event.consumer.model.Course;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private String message;

    @KafkaListener(topics = "madhankafka", groupId = "consumer-group")
    public void course(Course course) {
        message = course + " Got the data from kafka";
        System.out.println(message);

    }

    public String getMessage() {
        return message;
    }
}
