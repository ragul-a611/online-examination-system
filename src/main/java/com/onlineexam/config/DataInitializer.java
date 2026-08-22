package com.onlineexam.config;

import com.onlineexam.entity.Admin;
import com.onlineexam.entity.Question;
import com.onlineexam.repository.AdminRepository;
import com.onlineexam.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final QuestionRepository questionRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AdminRepository adminRepository, QuestionRepository questionRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.questionRepository = questionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize Default Admin
        if (adminRepository.findByUsername("admin").isEmpty()) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");
            adminRepository.save(admin);
        }

        // Initialize Sample Questions
        if (questionRepository.count() == 0) {
            List<Question> sampleQuestions = Arrays.asList(
                    new Question(null, "What is the size of int variable in Java?", "8 bit", "16 bit", "32 bit", "64 bit", "C", "Java", "Easy"),
                    new Question(null, "What is the default value of local variables?", "null", "0", "Depends on type", "Not assigned", "D", "Java", "Medium"),
                    new Question(null, "Which method can be used to find the length of a string in Java?", "getSize()", "length()", "size()", "len()", "B", "Java", "Easy"),
                    new Question(null, "Which of the following is not a Java features?", "Dynamic", "Architecture Neutral", "Use of pointers", "Object-oriented", "C", "Java", "Easy"),
                    new Question(null, "What does HTML stand for?", "Hyper Text Markup Language", "Hyperlinks and Text Markup Language", "Home Tool Markup Language", "Hyper Text Make Language", "A", "HTML", "Easy"),
                    new Question(null, "Who is making the Web standards?", "Mozilla", "Microsoft", "The World Wide Web Consortium", "Google", "C", "HTML", "Medium"),
                    new Question(null, "Choose the correct HTML element for the largest heading:", "<heading>", "<h6>", "<head>", "<h1>", "D", "HTML", "Easy"),
                    new Question(null, "What does CSS stand for?", "Computer Style Sheets", "Cascading Style Sheets", "Colorful Style Sheets", "Creative Style Sheets", "B", "CSS", "Easy"),
                    new Question(null, "Where in an HTML document is the correct place to refer to an external style sheet?", "In the <body> section", "At the end of the document", "In the <head> section", "In the <html> section", "C", "CSS", "Medium"),
                    new Question(null, "Which HTML tag is used to define an internal style sheet?", "<script>", "<css>", "<style>", "<link>", "C", "CSS", "Easy"),
                    new Question(null, "Inside which HTML element do we put the JavaScript?", "<js>", "<scripting>", "<script>", "<javascript>", "C", "JavaScript", "Easy"),
                    new Question(null, "Where is the correct place to insert a JavaScript?", "The <body> section", "The <head> section", "Both the <head> section and the <body> section are correct", "None of the above", "C", "JavaScript", "Medium"),
                    new Question(null, "What is a correct syntax to output 'Hello World' in JavaScript?", "print('Hello World');", "echo('Hello World');", "console.log('Hello World');", "response.write('Hello World');", "C", "JavaScript", "Easy"),
                    new Question(null, "Which of the following is not a valid SQL type?", "FLOAT", "NUMERIC", "DECIMAL", "CHARACTER", "D", "DBMS", "Medium"),
                    new Question(null, "What does SQL stand for?", "Structured Query Language", "Strong Question Language", "Structured Question Language", "Strong Query Language", "A", "DBMS", "Easy"),
                    new Question(null, "Which SQL statement is used to update data in a database?", "SAVE", "MODIFY", "UPDATE", "SAVE AS", "C", "DBMS", "Easy"),
                    new Question(null, "Which SQL statement is used to delete data from a database?", "COLLAPSE", "DELETE", "REMOVE", "DROP", "B", "DBMS", "Easy"),
                    new Question(null, "In Java, what is the superclass of all classes?", "Object", "Class", "Main", "System", "A", "Java", "Easy"),
                    new Question(null, "What is encapsulation in OOP?", "Hiding implementation details", "Creating new classes", "Deleting classes", "None of the above", "A", "Java", "Medium"),
                    new Question(null, "Which database constraint ensures that all values in a column are unique?", "PRIMARY KEY", "FOREIGN KEY", "UNIQUE", "CHECK", "C", "DBMS", "Medium")
            );
            questionRepository.saveAll(sampleQuestions);
        }
    }
}
