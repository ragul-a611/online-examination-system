package com.onlineexam.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        boolean isAdmin = false;
        boolean isStudent = false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_ADMIN".equals(auth.getAuthority())) {
                isAdmin = true;
                break;
            }
            if ("ROLE_STUDENT".equals(auth.getAuthority())) {
                isStudent = true;
                break;
            }
        }

        if (isAdmin) {
            response.sendRedirect("/admin/dashboard");
        } else if (isStudent) {
            response.sendRedirect("/student/dashboard");
        } else {
            response.sendRedirect("/");
        }
    }
}
