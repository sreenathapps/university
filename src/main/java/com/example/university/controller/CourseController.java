package com.example.university.controller;

import com.example.university.model.Course;
import com.example.university.model.Professor;
import com.example.university.model.Student;
import com.example.university.service.CourseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseJpaService courseJpaService;

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return courseJpaService.getCourses();
    }
    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course) {
        return courseJpaService.addCourse(course);
    }
    @GetMapping("/courses/{id}")
    public Course getCourse(@PathVariable int id) {
        return courseJpaService.getCourseById(id);
    }
    @PutMapping("/courses/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course course) {
        return courseJpaService.updateCourse(id, course);
    }
    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseJpaService.deleteCourse(id);
    }
    @GetMapping("/courses/{id}/students")
    public List<Student> getCourseStudents(@PathVariable int id) {
        return courseJpaService.getCourseStudents(id);
    }
    @GetMapping("/courses/{id}/professor")
    public Professor getCourseProfessor(@PathVariable int id) {
        return courseJpaService.getCourseProfessor(id);
    }
}