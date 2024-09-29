package com.example.university.controller;

import com.example.university.model.Course;
import com.example.university.model.Professor;
import com.example.university.service.ProfessorJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfessorController {
    @Autowired
    private ProfessorJpaService professorJpaService;

    @GetMapping("/professors")
    public List<Professor> getProfessors() {
        return professorJpaService.getProfessors();
    }

    @PostMapping("/professors")
    public Professor addProfessor(@RequestBody Professor prof) {
        return professorJpaService.addProfessor(prof);
    }

    @GetMapping("/professors/{id}")
    public Professor getProfessor(@PathVariable int id) {
        return professorJpaService.getProfessorById(id);
    }

    @PutMapping("/professors/{id}")
    public Professor updateProfessor(@PathVariable int id, @RequestBody Professor prof) {
        return professorJpaService.updateProfessor(id, prof);
    }

    @DeleteMapping("/professors/{id}")
    public void deleteProf(@PathVariable int id) {
        professorJpaService.deleteProfessor(id);
    }

    @GetMapping("/professors/{profId}/courses")
    public List<Course> getProfessorCourses(@PathVariable int profId) {
        return professorJpaService.getProfessorCourses(profId);
    }
}