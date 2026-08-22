package com.onlineexam.controller;

import com.onlineexam.entity.Student;
import com.onlineexam.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final StudentService studentService;

    public AuthController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/student-login")
    public String studentLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid email or password.");
        }
        return "student-login";
    }

    @GetMapping("/admin-login")
    public String adminLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "admin-login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-register";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute("student") Student student, Model model) {
        try {
            studentService.registerStudent(student);
            return "redirect:/student-login?registered=true";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "student-register";
        }
    }
}
