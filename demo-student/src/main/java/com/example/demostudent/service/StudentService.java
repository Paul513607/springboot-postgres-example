package com.example.demostudent.service;

import com.example.demostudent.dao.StudentDao;
import com.example.demostudent.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentDao studentDao;

    @Autowired
    public StudentService(@Qualifier("postgresDao") StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int addStudent(Student student) {
        return studentDao.addStudentGenId(student);
    }

    public List<Student> listAllStudents() {
        return studentDao.listAllStudents();
    }

    public Optional<Student> selectStudentById(UUID id) {
        return studentDao.selectStudentById(id);
    }

    public int deleteStudentById(UUID id) {
        return studentDao.deleteStudentById(id);
    }

    public int updateStudentById(UUID id, Student studentToUpdate) {
        return studentDao.updateStudentById(id, studentToUpdate);
    }
}
