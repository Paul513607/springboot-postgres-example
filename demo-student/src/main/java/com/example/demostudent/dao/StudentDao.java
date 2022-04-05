package com.example.demostudent.dao;

import com.example.demostudent.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentDao {
    default int addStudentGenId(Student student) {
        UUID id = UUID.randomUUID();
        return addStudent(id, student);
    }

    int addStudent(UUID id, Student student);

    List<Student> listAllStudents();

    Optional<Student> selectStudentById(UUID id);

    int deleteStudentById(UUID id);

    int updateStudentById(UUID id, Student student);
}
