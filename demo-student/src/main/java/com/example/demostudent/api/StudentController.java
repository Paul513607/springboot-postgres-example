package com.example.demostudent.api;

import com.example.demostudent.model.Student;
import com.example.demostudent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public void addStudent(@Valid @NotNull @RequestBody Student student) {
        studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> listAllStudents() {
        return studentService.listAllStudents();
    }

    @GetMapping(path = "{id}")
    public Student selectStudentById(@PathVariable("id") UUID id) {
        return studentService.selectStudentById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudentById(@PathVariable("id") UUID id) {
        studentService.deleteStudentById(id);
    }

    @PutMapping(path = "{id}")
    public void updateStudentById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Student studentToUpdate) {
        studentService.updateStudentById(id, studentToUpdate);
    }
}
