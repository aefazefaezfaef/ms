package com.alibou.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final KafkaProducer kafkaProducer;

    public void saveStudent(Student student) {
        Student student1 = repository.save(student);
        kafkaProducer.sendFlightEvent(String.valueOf(student1.getSchoolId()));
    }

    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    public List<Student> findAllStudentsBySchool(Integer schoolId) {
        return repository.findAllBySchoolId(schoolId);
    }
}
