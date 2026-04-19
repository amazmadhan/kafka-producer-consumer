package com.event.producer.service;

import com.event.producer.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, Course> kafkaTemplate;

    public String sendMessage(Course course) {
        kafkaTemplate.send("madhankafka", "course", course);
        return "Course message sent to kafka server";
    }
}
