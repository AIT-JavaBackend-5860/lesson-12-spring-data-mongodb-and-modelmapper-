package ait.cohort5860.student.service;

import ait.cohort5860.student.dao.StudentRepository;
import ait.cohort5860.student.dto.ScoreDto;
import ait.cohort5860.student.dto.StudentCredentialsDto;
import ait.cohort5860.student.dto.StudentDto;
import ait.cohort5860.student.dto.StudentUpdateDto;
import ait.cohort5860.student.dto.exceptions.NotFoundException;
import ait.cohort5860.student.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


// @Component
// @RequiredArgsConstructor
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public Boolean addStudent(StudentCredentialsDto studentCredentialsDto) {
        if (studentRepository.findById(studentCredentialsDto.getId()).isPresent()) {
            return false;
        }
        Student student = new Student(studentCredentialsDto.getId(), studentCredentialsDto.getName(), studentCredentialsDto.getPassword());
        studentRepository.save(student);
        return null;
    }

    @Override
    public StudentDto findStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(NotFoundException::new);
        return new StudentDto(student.getId(), student.getName(), student.getScores());
    }

    @Override
    public StudentDto removeStudent(Long id) {

        // find the student by ID
        Student student = studentRepository.findById(id)
                .orElseThrow(NotFoundException::new); // if not found, throw NotFoundException

        // Delete the student from the repository
        studentRepository.deleteById(id);

        // Return student dto data
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getScores()
        );
    }

    @Override
    public StudentCredentialsDto updateStudent(Long id, StudentUpdateDto studentUpdateDto) {

        // Find the student
        Student student = studentRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        // Update name
        if (studentUpdateDto.getName() != null) {
            student.setName(studentUpdateDto.getName());
        }

        // Update password
        if (studentUpdateDto.getPassword() != null) {
            student.setPassword(studentUpdateDto.getPassword());
        }

        // Save student
        studentRepository.save(student);

        // Return student data
        return new StudentCredentialsDto(
                student.getId(),
                student.getName(),
                student.getPassword()
        );
    }

    @Override
    public Boolean addScore(Long id, ScoreDto scoreDto) {
        // Find student
        Student student = studentRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        // Add score
        boolean added = student.addScore(scoreDto.getExamName(), scoreDto.getScore());

        // Save updated student
        studentRepository.save(student);
        // studentRepository. - checkout all available methods

        return added;
    }



    @Override
    public List<StudentDto> findStudentsByName(String name) {

        return studentRepository.findByNameIgnoreCase(name) // find via DB
                .map(
                        s -> new StudentDto(
                                s.getId(),
                                s.getName(),
                                s.getScores())
                )
                .toList();

        /* return studentRepository.findAll().stream()
                .filter(s -> name.equalsIgnoreCase(s.getName()))
                .map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
                .toList();
         */
    }



    @Override
    public Long countStudentsByNames(Set<String> names) {

        return studentRepository.countByNameInIgnoreCase(names);

        /*
        // Get all students
        List<Student> students = studentRepository.findAll();

        // Count students
        return students.stream()
                .filter(student -> names.contains(student.getName()))
                .count();

         */
    }

    @Override
    public List<StudentDto> findStudentsByExamNameMinScore(String examName, Integer minScore) {
        // All students by ExamName and minimum score via DB
        return studentRepository.findByExamAndScoresGreaterThan(examName, minScore)
                .map(student -> new StudentDto(
                        student.getId(),
                        student.getName(),
                        student.getScores()))
                .toList();
    }
}
