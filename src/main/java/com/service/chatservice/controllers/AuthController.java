package com.service.chatservice.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.chatservice.configuration.security.auth.JWTUtil;
import com.service.chatservice.configuration.security.auth.ResponseError;
import com.service.chatservice.domain.security.AuthDto;
import com.service.chatservice.domain.security.AuthResultDto;
import com.service.chatservice.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.POST})
public class AuthController {
    
    private final JWTUtil jwtUtil;
    private final UserService aService;
    private final AuthenticationManager aManager;

    @PostMapping("/login")
    public ResponseEntity<AuthResultDto> logIn(@RequestBody AuthDto aDto){
        
        var authenticationToken = new UsernamePasswordAuthenticationToken(aDto.getUsername(),aDto.getPassword());
        
        aManager.authenticate(authenticationToken);

        var p = aService.findByUsername(aDto.getUsername());
        

        String jwt = jwtUtil.generateToken(p.getUsername(), p.getRole());

        return ResponseEntity.ok().body(AuthResultDto.builder()
            .userId(p.getId())
            .token(jwt).build());

    }


    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<ResponseError> handleException(BadCredentialsException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError("Bad login or password",LocalDateTime.now().toString()));
    }


}
