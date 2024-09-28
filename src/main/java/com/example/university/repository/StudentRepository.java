package com.example.university.repository;

import com.example.university.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository {
    List<Student> getStudents();
    Student getStudentById(int id);
    Student addStudent(Student student);
}