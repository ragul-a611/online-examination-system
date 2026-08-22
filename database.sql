-- Database creation script
-- Run this script in your MySQL server if the database is not created automatically

CREATE DATABASE IF NOT EXISTS online_exam_db;
USE online_exam_db;

-- Note: Tables will be created automatically by Hibernate / Spring Data JPA 
-- due to spring.jpa.hibernate.ddl-auto=update property in application.properties.
-- Sample data will also be initialized via CommandLineRunner in the application.

-- If you want to create them manually, here is a reference:
/*
CREATE TABLE `admins` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_username` (`username`)
);

CREATE TABLE `students` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `register_number` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_email` (`email`),
  UNIQUE KEY `UK_register_number` (`register_number`)
);
*/
