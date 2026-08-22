package com.onlineexam.repository;

import com.onlineexam.entity.Result;
import com.onlineexam.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByStudentOrderByExamDateDesc(Student student);
    Long countByStudent(Student student);
}
