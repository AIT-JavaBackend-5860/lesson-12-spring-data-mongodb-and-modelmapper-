package ait.cohort5860.student.dao;

import ait.cohort5860.student.model.Student;
import io.micrometer.common.KeyValues;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, Long> {


}
