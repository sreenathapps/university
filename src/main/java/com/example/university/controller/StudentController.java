package com.example.university.controller;

import com.example.university.model.Course;
import com.example.university.model.Student;
import com.example.university.service.StudentJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentJpaService studentJpaService;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentJpaService.getStudents();
    }
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        return studentJpaService.addStudent(student);
    }
    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id) {
        return studentJpaService.getStudentById(id);
    }
    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        return studentJpaService.updateStudent(id, student);
    }
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentJpaService.deleteStudent(id);
    }
    @GetMapping("/students/{id}/courses")
    public List<Course> getStudentCourses(@PathVariable int id) {
        return studentJpaService.getStudentCourses(id);
    }
}