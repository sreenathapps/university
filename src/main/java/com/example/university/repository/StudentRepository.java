package com.example.university.repository;

import com.example.university.model.Course;
import com.example.university.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository {
    List<Student> getStudents();
    Student getStudentById(int id);
    Student addStudent(Student student);
    void deleteStudent(int id);
    List<Course> getStudentCourses (int studId);
    Student updateStudent(int id, Student student);
}