package com.service.chatservice.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.chatservice.domain.user.dto.NewUserDto;
import com.service.chatservice.domain.user.dto.UserDto;
import com.service.chatservice.domain.user.mapper.MapperUser;
import com.service.chatservice.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*",
    methods = {RequestMethod.GET,RequestMethod.POST})
public class UserController {
    
    private final UserService service;
    private final MapperUser mapperUser;

    @PostMapping("/new")
    public ResponseEntity<UserDto> newUser(@RequestBody NewUserDto user){
        
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperUser
            .map(service.newUser(mapperUser.map(user))));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<UserDto> findByName(@PathVariable("name") String  username){
        return ResponseEntity.ok(mapperUser.map(service.findByUsername(username)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findByid(@PathVariable("id") long id){
        return ResponseEntity.ok(mapperUser.map(service.findByid(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(service.findAll().stream()
            .map(mapperUser::map)
            .toList());
    }
}
