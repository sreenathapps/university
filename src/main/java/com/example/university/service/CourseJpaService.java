package com.example.university.service;

import com.example.university.model.Course;
import com.example.university.model.Professor;
import com.example.university.model.Student;
import com.example.university.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseJpaService implements CourseRepository {
    @Override
    public List<Course> getCourses() {
        return List.of();
    }

    @Override
    public Course getCourseById(int id) {
        return null;
    }

    @Override
    public Course addCourse(Course course) {
        return null;
    }

    @Override
    public Course updateCourse(int id, Course course) {
        return null;
    }

    @Override
    public void deleteCourse(int id) {

    }

    @Override
    public List<Student> getCourseStudents(int courseId) {
        return List.of();
    }

    @Override
    public Professor getCourseProfessor(int courseId) {
        return null;
    }
}