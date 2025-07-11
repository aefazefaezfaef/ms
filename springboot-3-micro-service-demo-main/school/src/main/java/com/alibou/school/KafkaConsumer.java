package com.alibou.school;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SchoolService schoolService;

    @KafkaListener(topics = "new-student", groupId = "group1")
    public void flightEventConsumer(String message) {
        log.info("Consumer consume Kafka message -> {}", message);
        School school = schoolService.findSchoolById(Integer.valueOf(message));
        school.setStudentsNumber(school.getStudentsNumber() + 1);
        schoolService.saveSchool(school);
    }
}
