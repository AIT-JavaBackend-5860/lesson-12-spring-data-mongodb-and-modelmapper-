package ait.cohort5860.student.dao;

import ait.cohort5860.student.model.Student;
import io.micrometer.common.KeyValues;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.*;
import java.util.stream.Stream;


public interface StudentRepository extends MongoRepository<Student, Long>{

    Stream<Student> findByNameIgnoreCase(String name);
    long countByNameInIgnoreCase(Set<String> names);

    @Query("{ 'scores.?0': { $gt: ?1 } }")
//    @Query("{'scores.Math':{'$gt':90}}")
    Stream<Student> findByExamAndScoresGreaterThan(String examName, Integer score);
}
