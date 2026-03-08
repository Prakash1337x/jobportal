package com.example.jobportal.security;

import com.example.jobportal.entity.User;
import com.example.jobportal.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private UserRepository userRepository;

    public JwtFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")){
           String token = authHeader.substring(7);
           String email = jwtUtil.extractEmail(token);
           String role = jwtUtil.extractRole(token);

            System.out.println("Token Role Claim: " + role);
            System.out.println("Token Email: " + email);

           try {
               if(email != null && role != null){
                   UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                           email, null, List.of(new SimpleGrantedAuthority(role)));
                   SecurityContextHolder.getContext().setAuthentication(authToken);
               }
           } catch (Exception e) {
               System.out.println("Inavlid or Token Expired");
           }
        }
        filterChain.doFilter(request, response);
    }
}
