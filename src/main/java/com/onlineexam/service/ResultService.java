package com.onlineexam.service;

import com.onlineexam.entity.Result;
import com.onlineexam.entity.Student;
import com.onlineexam.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public void saveResult(Result result) {
        resultRepository.save(result);
    }

    public List<Result> getResultsByStudent(Student student) {
        return resultRepository.findByStudentOrderByExamDateDesc(student);
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Long countExamsByStudent(Student student) {
        return resultRepository.countByStudent(student);
    }
    
    public Result getBestResultByStudent(Student student) {
        return resultRepository.findByStudentOrderByExamDateDesc(student)
                .stream()
                .max((r1, r2) -> r1.getScore().compareTo(r2.getScore()))
                .orElse(null);
    }
    
    public Result getLatestResultByStudent(Student student) {
        List<Result> results = resultRepository.findByStudentOrderByExamDateDesc(student);
        return results.isEmpty() ? null : results.get(0);
    }
}
