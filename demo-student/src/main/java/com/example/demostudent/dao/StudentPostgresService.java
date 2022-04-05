package com.example.demostudent.dao;

import com.example.demostudent.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresDao")
public class StudentPostgresService implements StudentDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentPostgresService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addStudent(UUID id, Student student) {
        final String insertSql = "INSERT INTO student (id, name) VALUES (?, ?)";
        Object[] args = new Object[] {id, student.getName()};

        return jdbcTemplate.update(insertSql, args);
    }

    @Override
    public List<Student> listAllStudents() {
        final String selectSql = "SELECT id, name FROM student";

        return jdbcTemplate.query(selectSql,  (rs, rowNum) -> {
            UUID id = UUID.fromString(rs.getString("id"));
            String name = rs.getString("name");
            return new Student(id, name);
        });

    }

    @Override
    public Optional<Student> selectStudentById(UUID id) {
        final String selectSql = "SELECT id, name FROM student WHERE id = ?";

        Student student = jdbcTemplate.queryForObject(selectSql, (rs, rowNum) -> {
            UUID studentId = UUID.fromString(rs.getString("id"));
            String name = rs.getString("name");
            return new Student(studentId, name);
        }, id);
        return Optional.ofNullable(student);
    }

    @Override
    public int deleteStudentById(UUID id) {
        final String deleteSql = "DELETE FROM student WHERE id = ?";
        Object[] args = new Object[] {id};

        return jdbcTemplate.update(deleteSql, args);
    }

    @Override
    public int updateStudentById(UUID id, Student student) {
        final String insertSql = "UPDATE student SET id=?, name=? WHERE id=?";
        Object[] args = new Object[] {id, student.getName(), id};

        return jdbcTemplate.update(insertSql, args);
    }
}
