package com.onlineexam.repository;

import com.onlineexam.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySubject(String subject);
    List<Question> findByDifficulty(String difficulty);
    
    @Query(value = "SELECT * FROM questions ORDER BY RAND() LIMIT ?1", nativeQuery = true)
    List<Question> findRandomQuestions(int limit);
}
