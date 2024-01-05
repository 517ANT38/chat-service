package com.service.chatservice.configuration.security.filters;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.service.chatservice.configuration.security.auth.JWTUtil;
import com.service.chatservice.configuration.security.auth.ResponseError;
import com.service.chatservice.services.security.ChatUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final ChatUserDetailsService aService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String authHeader=request.getHeader("Authorization");
        if(authHeader!=null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);

            if (authHeader.isBlank()) {
                var r=new ResponseError("Invalid JWT Token in Bearer Header",
                        LocalDateTime.now().toString());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, r.toString());
                return;
            }
            else {
                try {
                    String username = jwtUtil.validateTokenAndRetrieveClaim(jwt);
                    var person = aService.loadUserByUsername(username);
                    var authenticationToken=new UsernamePasswordAuthenticationToken(
                            person.getPassword(),person.getUsername(),
                            person.getAuthorities());
                    if(SecurityContextHolder.getContext().getAuthentication()==null){
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }

                }catch (JWTVerificationException j){
                    var r=new ResponseError("Invalid JWT Token in Bearer Header",
                            LocalDateTime.now().toString());
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,r.toString());
                    return;

                }
            }

        }

        filterChain.doFilter(request, response);
        
        
    }
    
}
