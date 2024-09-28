package com.example.university.service;

import com.example.university.model.Course;
import com.example.university.model.Professor;
import com.example.university.repository.CourseJpaRepository;
import com.example.university.repository.ProfessorJpaRepository;
import com.example.university.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ProfessorJpaService implements ProfessorRepository {
    @Autowired
    private ProfessorJpaRepository professorJpaRepository;

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Override
    public List<Professor> getProfessors() {
        return professorJpaRepository.findAll();
    }

    @Override
    public Professor getProfessorById(int id) {
        return professorJpaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public Professor updateProfessor(int id, Professor professor) {
        try {
            Professor newProf = professorJpaRepository.findById(id).get();
            if (professor.getProfessorName() != null) {
                newProf.setProfessorName(professor.getProfessorName());
            }
            if (professor.getDepartment() != null) {
                newProf.setDepartment(professor.getDepartment());
            }
            return professorJpaRepository.save(newProf);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Professor addProfessor(Professor professor) {
        return professorJpaRepository.save(professor);
    }

    @Override
    public void deleteProfessor(int id) {
        Professor professor = professorJpaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        List<Course> courses = courseJpaRepository.findByProfessor(professor);
        for (Course course : courses) {
            course.setProfessor(null);
        }
        courseJpaRepository.saveAll(courses);
        professorJpaRepository.deleteById(id);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Course> getProfessorCourses(int professorId) {
        Professor professor = professorJpaRepository.findById(professorId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return courseJpaRepository.findByProfessor(professor);
    }
}