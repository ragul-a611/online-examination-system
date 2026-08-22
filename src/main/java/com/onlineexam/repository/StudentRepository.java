package com.onlineexam.repository;

import com.onlineexam.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByRegisterNumber(String registerNumber);
    boolean existsByEmail(String email);
    boolean existsByRegisterNumber(String registerNumber);
}
