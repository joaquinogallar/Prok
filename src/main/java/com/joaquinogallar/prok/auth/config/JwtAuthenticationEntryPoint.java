package com.joaquinogallar.prok.auth.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * Handles unauthorized requests by redirecting to the login endpoint.
     *
     * This method is invoked when:
     * 1. An unauthenticated user attempts to access a protected resource
     * 2. A JWT token is invalid or expired
     * 3. No authentication credentials are provided
     *
     * @param request The HTTP request that resulted in an AuthenticationException
     * @param response The HTTP response that will be modified to redirect to login
     * @param authException The exception that caused the invocation (may contain error details)
     * @throws IOException If an I/O error occurs during redirection
     * @throws ServletException If a general servlet error occurs
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // Redirects to login endpoint with an error parameter
        response.sendRedirect("/auth/login?error=unauthorized");
    }
}
