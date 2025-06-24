package ait.cohort5860.student.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode(of = "id")
@Document(collection = "Students")
public class Student {

    // @Id - primary key field
    private long id;
    @Setter
    private String name;
    @Setter
   private String password;
   private Map<String,Integer> scores = new HashMap<>(); // storage students in map

    public Student(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public boolean addScore(String examName, Integer score){

        return scores.put(examName, score) == null;


    }

}
