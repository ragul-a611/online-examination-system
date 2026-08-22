package com.onlineexam.security;

import com.onlineexam.entity.Admin;
import com.onlineexam.entity.Student;
import com.onlineexam.repository.AdminRepository;
import com.onlineexam.repository.StudentRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;

    public CustomUserDetailsService(AdminRepository adminRepository, StudentRepository studentRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // First check in Admin
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            GrantedAuthority authority = new SimpleGrantedAuthority(admin.getRole());
            return new CustomUserDetails(admin.getId(), admin.getUsername(), admin.getPassword(), "Admin", Collections.singletonList(authority));
        }

        // Then check in Student by email
        Optional<Student> studentOpt = studentRepository.findByEmail(username);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            GrantedAuthority authority = new SimpleGrantedAuthority(student.getRole());
            return new CustomUserDetails(student.getId(), student.getEmail(), student.getPassword(), student.getFullName(), Collections.singletonList(authority));
        }

        throw new UsernameNotFoundException("User not found with username/email: " + username);
    }
}
