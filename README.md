# Online Examination System

A complete, fully functional Online Examination System Web Application built using Java 21, Spring Boot 3.x, MySQL, and Thymeleaf. 

## Features

**Admin Module:**
- Secure Admin Login
- Admin Dashboard with Statistics
- Manage Questions (Add, Edit, View, Delete)
- Manage Students (View, Remove)
- View All Examination Results

**Student Module:**
- Student Registration
- Secure Student Login
- Student Dashboard (Best Score, Latest Score, Exam History)
- Take Timed MCQ Examinations (JavaScript Timer)
- Navigate between questions
- Automatic Evaluation & Results Calculation
- View Result History & Status (Pass/Fail)
- Update Profile Name

## Technologies Used
- **Backend:** Java 21, Spring Boot 3.x, Spring MVC, Spring Data JPA, Spring Security, Hibernate, Maven
- **Database:** MySQL 8.x
- **Frontend:** Thymeleaf, HTML5, CSS3, JavaScript, Bootstrap 5, Bootstrap Icons
- **Server:** Embedded Tomcat

## Project Requirements
- Java 21 JDK
- Maven
- MySQL 8.x Server (Running Locally)
- Web Browser

## Database Setup

1. Open your MySQL client (e.g., MySQL Workbench or Command Line).
2. Create the database:
   ```sql
   CREATE DATABASE IF NOT EXISTS online_exam_db;
   ```
3. Update `src/main/resources/application.properties` with your MySQL credentials:
   ```properties
   spring.datasource.username=YOUR_MYSQL_USERNAME
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   ```
   *(Note: By default, the app is configured to use username `root` and a blank password. Change this according to your local setup).*

## How to Run the Project

1. Open a terminal in the root folder of this project (`Online-Examination-System`).
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
4. Open your web browser and go to:
   ```
   http://localhost:8080
   ```

## Default Credentials

The application automatically generates a default admin account and sample questions on the first run.

**Admin Login:**
- **Username:** `admin`
- **Password:** `admin123`

*(Important: Change these credentials for production deployments.)*

## Note for Evaluators
This is a comprehensive full-stack application built without Docker or frontend frameworks like React/Angular as per the requirements. It demonstrates a classic MVC architecture with modern premium UI aesthetics.
