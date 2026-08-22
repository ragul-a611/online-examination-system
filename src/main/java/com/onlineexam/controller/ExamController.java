package com.onlineexam.controller;

import com.onlineexam.entity.Question;
import com.onlineexam.entity.Result;
import com.onlineexam.entity.Student;
import com.onlineexam.security.CustomUserDetails;
import com.onlineexam.service.QuestionService;
import com.onlineexam.service.ResultService;
import com.onlineexam.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student/exam")
public class ExamController {

    private final QuestionService questionService;
    private final StudentService studentService;
    private final ResultService resultService;

    public ExamController(QuestionService questionService, StudentService studentService, ResultService resultService) {
        this.questionService = questionService;
        this.studentService = studentService;
        this.resultService = resultService;
    }

    @GetMapping("/start")
    public String startExam(HttpSession session) {
        List<Question> questions = questionService.getRandomQuestions(10);
        session.setAttribute("examQuestions", questions);
        session.setAttribute("studentAnswers", new HashMap<Long, String>());
        session.setAttribute("examStartTime", LocalDateTime.now());
        return "redirect:/student/exam/question/0";
    }

    @GetMapping("/question/{index}")
    public String showQuestion(@PathVariable("index") int index, HttpSession session, Model model) {
        List<Question> questions = (List<Question>) session.getAttribute("examQuestions");
        if (questions == null || index < 0 || index >= questions.size()) {
            return "redirect:/student/dashboard";
        }

        Map<Long, String> answers = (Map<Long, String>) session.getAttribute("studentAnswers");
        Question currentQuestion = questions.get(index);

        model.addAttribute("question", currentQuestion);
        model.addAttribute("currentIndex", index);
        model.addAttribute("totalQuestions", questions.size());
        model.addAttribute("selectedAnswer", answers.get(currentQuestion.getId()));
        
        // Calculate remaining time
        LocalDateTime startTime = (LocalDateTime) session.getAttribute("examStartTime");
        long elapsedSeconds = java.time.Duration.between(startTime, LocalDateTime.now()).getSeconds();
        long remainingSeconds = Math.max(0, (10 * 60) - elapsedSeconds);
        model.addAttribute("remainingSeconds", remainingSeconds);

        return "exam";
    }

    @PostMapping("/answer")
    public String saveAnswer(@RequestParam("questionIndex") int index,
                             @RequestParam("questionId") Long questionId,
                             @RequestParam(value = "answer", required = false) String answer,
                             @RequestParam("action") String action,
                             HttpSession session) {
        
        Map<Long, String> answers = (Map<Long, String>) session.getAttribute("studentAnswers");
        if (answers != null && answer != null) {
            answers.put(questionId, answer);
        }

        if ("next".equals(action)) {
            return "redirect:/student/exam/question/" + (index + 1);
        } else if ("prev".equals(action)) {
            return "redirect:/student/exam/question/" + (index - 1);
        } else if ("submit".equals(action)) {
            return "redirect:/student/exam/submit";
        }
        
        return "redirect:/student/exam/question/" + index;
    }

    @GetMapping("/submit")
    public String submitExam(@AuthenticationPrincipal CustomUserDetails userDetails, HttpSession session, Model model) {
        List<Question> questions = (List<Question>) session.getAttribute("examQuestions");
        Map<Long, String> answers = (Map<Long, String>) session.getAttribute("studentAnswers");

        if (questions == null) {
            return "redirect:/student/dashboard";
        }

        int correct = 0;
        for (Question q : questions) {
            String studentAns = answers.get(q.getId());
            if (q.getCorrectAnswer().equals(studentAns)) {
                correct++;
            }
        }

        int total = questions.size();
        int wrong = total - correct;
        int score = correct * 10; // 10 points per question
        double percentage = ((double) correct / total) * 100;
        String status = percentage >= 50 ? "PASS" : "FAIL";

        Student student = studentService.getStudentById(userDetails.getId());

        Result result = new Result();
        result.setStudent(student);
        result.setTotalQuestions(total);
        result.setCorrectAnswers(correct);
        result.setWrongAnswers(wrong);
        result.setScore(score);
        result.setPercentage(percentage);
        result.setStatus(status);
        result.setExamDate(LocalDateTime.now());

        resultService.saveResult(result);

        // Clear session
        session.removeAttribute("examQuestions");
        session.removeAttribute("studentAnswers");
        session.removeAttribute("examStartTime");

        model.addAttribute("result", result);
        return "result";
    }
}
