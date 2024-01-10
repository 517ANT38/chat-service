package com.service.chatservice.controllers;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.chatservice.domain.message.dto.ChatNotification;
import com.service.chatservice.domain.message.dto.NewMessageDto;
import com.service.chatservice.domain.message.mapper.MapperMessage;
import com.service.chatservice.services.MessageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*",
    methods = {RequestMethod.OPTIONS})
public class ChatMessageController {
    
    private final SimpMessagingTemplate messagingTemplate;
    private final MapperMessage messageMapper;
    private final MessageService messageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload NewMessageDto chatMessage) {
        

        var saved = messageService.save(
            chatMessage.getUserId(),
            chatMessage.getChatId(),
            messageMapper.map(chatMessage)
        );
        messagingTemplate.convertAndSendToUser(
                saved.getChat().getName(),"/queue/messages",
                new ChatNotification(
                    saved.getChat().getId(),
                    saved.getUser().getId(),
                    saved.getUser().getName()
                ));
    }

}
