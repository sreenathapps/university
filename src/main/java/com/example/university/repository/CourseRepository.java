package com.example.university.repository;

import com.example.university.model.Course;
import com.example.university.model.Professor;
import com.example.university.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository {
    List<Course> getCourses();
    Course getCourseById(int id);
    Course addCourse(Course course);
    Course updateCourse(int id, Course course);
    void deleteCourse(int id);
    List<Student> getCourseStudents(int courseId);
    Professor getCourseProfessor(int courseId);
}