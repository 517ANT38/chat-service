package com.service.chatservice.services;

import org.springframework.stereotype.Service;

import com.service.chatservice.app.NotFoundException;
import com.service.chatservice.domain.message.Message;
import com.service.chatservice.domain.message.MessageStatus;
import com.service.chatservice.repositories.MessageRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    
    private final MessageRepo messageRepo;

    public Message save(Message message){
        return messageRepo.save(message);
    }

    public void updateStatusMessage(long id){
        var m = messageRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("Message not found with id=" + id));
        m.setStatus(MessageStatus.DELIVERED);
        messageRepo.save(m);
    }
}
