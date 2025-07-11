package com.alibou.student;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    public static final String TOPIC = "new-student";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendFlightEvent(String msg){
        String key = UUID.randomUUID().toString();
        kafkaTemplate.send(TOPIC, key, msg);
        log.info("Producer produced the message {}", msg);
        // write your handlers and post-processing logic, based on your use case
    }
}
