package com.example.university.service;

import com.example.university.model.Course;
import com.example.university.model.Professor;
import com.example.university.model.Student;
import com.example.university.repository.CourseJpaRepository;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.ProfessorJpaRepository;
import com.example.university.repository.StudentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseJpaService implements CourseRepository {
    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Autowired
    private StudentJpaRepository studentJpaRepository;

    @Autowired
    private ProfessorJpaRepository professorJpaRepository;

    @Override
    public List<Course> getCourses() {
        return courseJpaRepository.findAll();
    }

    @Override
    public Course getCourseById(int id) {
        return courseJpaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
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
        Course course = courseJpaRepository.findById(courseId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return course.getStudents();
    }

    @Override
    public Professor getCourseProfessor(int courseId) {
        Course course = courseJpaRepository.findById(courseId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return course.getProfessor();
    }
}