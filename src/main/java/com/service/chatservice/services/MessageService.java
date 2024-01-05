package com.service.chatservice.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.service.chatservice.app.NotFoundException;
import com.service.chatservice.domain.message.Message;
import com.service.chatservice.domain.message.MessageStatus;
import com.service.chatservice.repositories.ChatRepo;
import com.service.chatservice.repositories.MessageRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    
    private final MessageRepo messageRepo;
    private final ChatRepo chatRepo;

    public Message save(Message message){
        return messageRepo.save(message);
    }

    public void updateStatusMessage(long id){
        var m = messageRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("Message not found with id=" + id));
        m.setStatus(MessageStatus.DELIVERED);
        messageRepo.save(m);
    }

    public Message findById(long id){
        return messageRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("Message not found with id=" + id));
               
    }

    public Set<Message> findByChatId(long chatId){
        var chat = chatRepo.findById(chatId)
            .orElseThrow(() -> new NotFoundException("Message not found with id=" + chatId));
        return chat.getMessages(); 
    }
}
