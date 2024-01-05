package com.service.chatservice.controllers;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.chatservice.domain.message.Message;
import com.service.chatservice.domain.message.dto.MessageDto;
import com.service.chatservice.domain.message.dto.NewMessageDto;
import com.service.chatservice.domain.message.mapper.MapperMessage;
import com.service.chatservice.services.MessageService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*",
    methods = {RequestMethod.GET,RequestMethod.POST})
public class MessageController {
    private final MessageService service;
    private final MapperMessage mapperMessage;

    @PostMapping("/new")
    public ResponseEntity<MessageDto> save(@RequestBody NewMessageDto message){
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperMessage
            .map(service.save(mapperMessage.map(message))));
    }    

    @GetMapping("/{id}")
    public Message findById(@PathVariable("id") long id){
        return service.findById(id);
               
    }

    @GetMapping("/chat/{chatId}")
    public Set<Message> findByChatId(@PathVariable("chatId") long chatId){
        return service.findByChatId(chatId); 
    }

}
