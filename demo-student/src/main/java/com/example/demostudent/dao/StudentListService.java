package com.example.demostudent.dao;

import com.example.demostudent.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("listDao")
public class StudentListService implements StudentDao {
    private static final List<Student> studentList = new ArrayList<>();

    @Override
    public int addStudent(UUID id, Student student) {
        studentList.add(new Student(id, student.getName()));
        return 1;
    }

    @Override
    public List<Student> listAllStudents() {
        return studentList;
    }

    @Override
    public Optional<Student> selectStudentById(UUID id) {
        return studentList.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteStudentById(UUID id) {
        Optional<Student> studentOptional = selectStudentById(id);
        if (studentOptional.isEmpty()) {
            return 0;
        }
        studentList.remove(studentOptional.get());
        return 1;
    }

    @Override
    public int updateStudentById(UUID id, Student student) {
        return selectStudentById(id)
                .map(s -> {
                    int indexOfStudentToUpdate = studentList.indexOf(s);
                    if (indexOfStudentToUpdate >= 0) {
                        listAllStudents().set(indexOfStudentToUpdate, new Student(id, student.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
