package com.example.demo.rest;

import com.example.demo.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private static List<Student> students;

    @PostConstruct
    public void loadData() {
        students = generateStudents();
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {
        try {
            return students.get(studentId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getClass() + e.getMessage(), e);
        }

    }

    // TODO: Remove this Hardcoding and add DB for returning Student data.
    private List<Student> generateStudents() {
        List<Student> students = new ArrayList<>();

        students.add(new Student(0, "Anirudh", "Gambhir"));
        students.add(new Student(1, "Marshal", "Nanda"));
        students.add(new Student(2, "Hiten", "Kanwar"));

        return students;
    }
}
