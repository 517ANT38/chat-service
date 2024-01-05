package com.service.chatservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.service.chatservice.domain.message.mapper.MapperMessage;
import com.service.chatservice.services.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService service;
    private final MapperMessage mapperMessage;
}
