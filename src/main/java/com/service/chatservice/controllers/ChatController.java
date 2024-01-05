package com.service.chatservice.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.chatservice.domain.chat.dto.ChatDto;
import com.service.chatservice.domain.chat.dto.NewChatDto;
import com.service.chatservice.domain.chat.mapper.MapperChat;
import com.service.chatservice.services.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*",
    methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ChatController {
    
    private final ChatService service;
    private final MapperChat mapperChat;

    @PostMapping("/new")
    public ResponseEntity<ChatDto> newChat(@RequestBody NewChatDto chat){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapperChat.map(service.newChat(mapperChat.map(chat))));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ChatDto> findByName(@PathVariable("name") String name){
        return ResponseEntity.ok(mapperChat.map(service.findByName(name)));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ChatDto> findByid(@PathVariable("id") long id){
        return ResponseEntity.ok(mapperChat.map(service.findByid(id)));
    }

    @GetMapping
    public ResponseEntity<List<ChatDto>> findAll(){
        return ResponseEntity.ok(service.findAll().stream()
            .map(mapperChat::map)
            .toList());
    }

    @PutMapping("/{chatId}/add/user/{userId}")
    public ResponseEntity<Void> addUserIntoChat(@PathVariable("chatId") long chatId, 
        @PathVariable("userId")long userId){
            service.addUserIntoChat(chatId, userId);
            return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{chatId}/delete/user/{userId}")
    public ResponseEntity<Void> deleteUserIntoChat(@PathVariable("chatId") long chatId, 
        @PathVariable("userId")long userId){
            service.deleteUserIntoChat(chatId, userId);
            return ResponseEntity.ok().build();
    }
}
