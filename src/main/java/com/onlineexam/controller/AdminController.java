package com.onlineexam.controller;

import com.onlineexam.entity.Question;
import com.onlineexam.service.QuestionService;
import com.onlineexam.service.ResultService;
import com.onlineexam.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final StudentService studentService;
    private final QuestionService questionService;
    private final ResultService resultService;

    public AdminController(StudentService studentService, QuestionService questionService, ResultService resultService) {
        this.studentService = studentService;
        this.questionService = questionService;
        this.resultService = resultService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalStudents", studentService.getAllStudents().size());
        model.addAttribute("totalQuestions", questionService.getAllQuestions().size());
        model.addAttribute("totalResults", resultService.getAllResults().size());
        
        long totalScore = resultService.getAllResults().stream().mapToLong(r -> r.getScore()).sum();
        long count = resultService.getAllResults().size();
        double avgScore = count > 0 ? (double) totalScore / count : 0.0;
        
        model.addAttribute("avgScore", String.format("%.2f", avgScore));
        model.addAttribute("recentStudents", studentService.getAllStudents().stream().limit(5).toList());
        model.addAttribute("recentResults", resultService.getAllResults().stream().limit(5).toList());
        return "admin-dashboard";
    }

    @GetMapping("/questions")
    public String viewQuestions(Model model) {
        model.addAttribute("questions", questionService.getAllQuestions());
        return "question-list";
    }

    @GetMapping("/questions/add")
    public String showAddQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        return "question-form";
    }

    @PostMapping("/questions/save")
    public String saveQuestion(@ModelAttribute("question") Question question) {
        questionService.saveQuestion(question);
        return "redirect:/admin/questions";
    }

    @GetMapping("/questions/edit/{id}")
    public String showEditQuestionForm(@PathVariable("id") Long id, Model model) {
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            return "redirect:/admin/questions";
        }
        model.addAttribute("question", question);
        return "question-form";
    }

    @GetMapping("/questions/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Long id) {
        questionService.deleteQuestion(id);
        return "redirect:/admin/questions";
    }

    @GetMapping("/students")
    public String viewStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "student-list";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/admin/students";
    }

    @GetMapping("/results")
    public String viewResults(Model model) {
        model.addAttribute("results", resultService.getAllResults());
        return "result-list";
    }
}
