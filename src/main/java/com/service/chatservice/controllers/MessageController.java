package com.service.chatservice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.chatservice.domain.message.dto.MessageDto;
import com.service.chatservice.domain.message.mapper.MapperMessage;
import com.service.chatservice.services.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*",
    methods = {RequestMethod.GET,RequestMethod.POST})
public class MessageController {
    private final MessageService service;
    private final MapperMessage mapperMessage;
 

    @GetMapping("/{id}")
    public  ResponseEntity<MessageDto> findById(@PathVariable("id") long id){
        return ResponseEntity.ok(mapperMessage.map(service.findById(id)));
               
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MessageDto>> findByChatId(@PathVariable("chatId") long chatId){
        return ResponseEntity.ok(service.findByChatId(chatId)
            .stream().map(mapperMessage::map).toList()); 
    }

}
