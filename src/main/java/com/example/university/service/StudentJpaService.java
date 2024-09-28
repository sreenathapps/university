package com.example.university.service;

import com.example.university.model.Student;
import com.example.university.repository.StudentRepository;

import java.util.List;

public class StudentJpaService implements StudentRepository {
    @Override
    public List<Student> getStudents() {
        return List.of();
    }

    @Override
    public Student getStudentById(int id) {
        return null;
    }

    @Override
    public Student addStudent(Student student) {
        return null;
    }
}