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

import java.util.ArrayList;
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
        int id=0;
        if (course.getProfessor()!= null) {
            id = course.getProfessor().getProfessorId();
        }
        Professor pro = professorJpaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        course.setProfessor(pro);

        List<Integer> studentIds = new ArrayList<>();
        for(Student student: course.getStudents()) {
            studentIds.add(student.getStudentId());
        }

        List<Student> completeStudents = studentJpaRepository.findAllById(studentIds);
        if (studentIds.size() != completeStudents.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        course.setStudents(completeStudents);

        for (Student s: completeStudents) {
            s.getCourses().add(course);
        }
        Course savedCourse = courseJpaRepository.save(course);
        studentJpaRepository.saveAll(completeStudents);

        return savedCourse;
    }

    @Override
    public Course updateCourse(int id, Course course) {
        try {
            Course newCourse = courseJpaRepository.findById(id).get();

            if (course.getStudents() != null) {
//          removing old associations with students
                for (Student s : newCourse.getStudents()) {
                    s.getCourses().remove(newCourse);
                }
                studentJpaRepository.saveAll(newCourse.getStudents());

                List<Integer> studIds = new ArrayList<>();
                for (Student s : course.getStudents()) studIds.add(s.getStudentId());
//          fetching new Students
                List<Student> studs = studentJpaRepository.findAllById(studIds);
                if (studIds.size() != studs.size()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

//          adding new associations of students
                for (Student stud : studs) {
                    stud.getCourses().add(newCourse);
                }
                studentJpaRepository.saveAll(studs);
//          mapping new Students to course
                newCourse.setStudents(studs);
                courseJpaRepository.save(newCourse);
            }
            if (course.getCredits() != 0) {
                newCourse.setCredits(course.getCredits());
            }
            if (course.getCourseName() != null) {
                newCourse.setCourseName(course.getCourseName());
            }
            if (course.getProfessor() != null) {
                int profId = course.getProfessor().getProfessorId();
                newCourse.setProfessor(professorJpaRepository.findById(profId).get());
            }
            return courseJpaRepository.save(newCourse);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCourse(int id) {
        Course course = courseJpaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        for (Student s: course.getStudents()) {
            s.getCourses().remove(course);
        }
        studentJpaRepository.saveAll(course.getStudents());

        courseJpaRepository.deleteById(id);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
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