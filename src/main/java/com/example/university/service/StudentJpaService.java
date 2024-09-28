package com.example.university.service;

import com.example.university.model.Course;
import com.example.university.model.Student;
import com.example.university.repository.CourseJpaRepository;
import com.example.university.repository.StudentJpaRepository;
import com.example.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class StudentJpaService implements StudentRepository {
    @Autowired
    private StudentJpaRepository studentJpaRepository;

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Override
    public List<Student> getStudents() {
        return studentJpaRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        return studentJpaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public Student addStudent(Student student) {
        List<Integer> courseIds = new ArrayList<>();
        for (Course c: student.getCourses()) {
            courseIds.add(c.getCourseId());
        }

        List<Course> courses = courseJpaRepository.findAllById(courseIds);
        if (courses.size() != courseIds.size()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        student.setCourses(courses);

        return studentJpaRepository.save(student);
    }
}