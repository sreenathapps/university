package com.example.university.service;

import com.example.university.model.Course;
import com.example.university.model.Student;
import com.example.university.repository.CourseJpaRepository;
import com.example.university.repository.StudentJpaRepository;
import com.example.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
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

    public Student updateStudent(int id, Student student) {
        Student newStudent = studentJpaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        if (student.getStudentName() != null) {
            newStudent.setStudentName(student.getStudentName());
        }
        if (student.getEmail() != null) {
            newStudent.setEmail(student.getEmail());
        }
        if (student.getCourses() != null) {
            List<Integer> courseIds = new ArrayList<>();
            for (Course c: student.getCourses()) {
                courseIds.add(c.getCourseId());
            }

            List<Course> courses = courseJpaRepository.findAllById(courseIds);
            if (courseIds.size() != courses.size()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            newStudent.setCourses(courses);
        }

        return studentJpaRepository.save(newStudent);
    }

    public List<Course> getStudentCourses (int studId) {
        Student student = studentJpaRepository.findById(studId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return student.getCourses();
    }

    public void deleteStudent(int id) {
        Student student = studentJpaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        for (Course c : student.getCourses()) {
            c.getStudents().remove(student);
        }
        courseJpaRepository.saveAll(student.getCourses());
        studentJpaRepository.deleteById(id);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}