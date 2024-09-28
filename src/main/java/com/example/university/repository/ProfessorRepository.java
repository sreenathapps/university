package com.example.university.repository;

import com.example.university.model.Course;
import com.example.university.model.Professor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository {
    List<Professor> getProfessors();
    Professor getProfessorById(int id);
    Professor updateProfessor(int id, Professor professor);
    Professor addProfessor(Professor professor);
    void deleteProfessor(int id);
    List<Course> getProfessorCourses(int professorId);
}