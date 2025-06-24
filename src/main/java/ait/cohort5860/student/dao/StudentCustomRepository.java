package ait.cohort5860.student.dao;

import ait.cohort5860.student.model.Student;

import java.util.List;

public interface StudentCustomRepository {
    List<Student> findByExamScoreGreaterThan(String examName, Integer minScore);
}
