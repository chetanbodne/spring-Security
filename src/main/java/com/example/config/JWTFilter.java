package com.example.config;

import com.example.entity.Bad;
import com.example.repository.BadRepository;
import com.example.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {
private JWTService jwtService;
private BadRepository badRepository;
    public JWTFilter(JWTService jwtService, BadRepository badRepository) {
        this.jwtService = jwtService;
        this.badRepository = badRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
       // System.out.println(token);
if (token!=null&&token.startsWith("Bearer")){
    String tokenval = token.substring(8, token.length() - 1);
   // System.out.println(tokenval);
    String username = jwtService.getUsername(tokenval);
   // System.out.println(username);// agar username print nh ho raha honga to pahile token genrete krke debug krna chahije
    Optional<Bad> byUsername = badRepository.findByUsername(username);
    if (byUsername.isPresent()){
        Bad bad = byUsername.get();
        UsernamePasswordAuthenticationToken
                auth= new UsernamePasswordAuthenticationToken(bad,null,null);
        auth.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }


}
filterChain.doFilter(request,response);
    }
}
