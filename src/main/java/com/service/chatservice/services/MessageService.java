package com.service.chatservice.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.service.chatservice.app.NotFoundException;
import com.service.chatservice.domain.message.Message;
import com.service.chatservice.domain.message.MessageStatus;
import com.service.chatservice.repositories.ChatRepo;
import com.service.chatservice.repositories.MessageRepo;
import com.service.chatservice.repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    
    private final UserRepo userRepo;
    private final ChatRepo chatRepo;
    private final MessageRepo messageRepo;

    public Message save(long userId, long chatId,Message message){
        var chat = chatRepo.findById(chatId)
            .orElseThrow(() -> new NotFoundException("Chat not found with id=" + chatId));
        var user = userRepo.findById(userId)
            .orElseThrow(() -> new NotFoundException("User not found with id=" + userId));
        message.toBuilder()
            .chat(chat)
            .user(user)
            .build();
        return messageRepo.save(message);
    }

    public void updateStatusMessage(long id){
        var m = messageRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("Message not found with id=" + id));
        m.setStatus(MessageStatus.DELIVERED);
        messageRepo.save(m);
    }

    public Message findById(long id){
        return messageRepo.findById(id).map(x -> messageRepo.save(x.toBuilder()
            .status(MessageStatus.DELIVERED).build()))
            .orElseThrow(() -> new NotFoundException("Message not found with id=" + id));
               
    }

    public Set<Message> findByChatId(long chatId){
        return messageRepo.findMessagesByChatId(chatId).stream()
            .map(x -> messageRepo.save(x.toBuilder().status(MessageStatus.DELIVERED).build()))
            .collect(Collectors.toSet());
    }

    public long countNewMessage(long chatId){
        return messageRepo.countNewMessage(chatId, MessageStatus.RECEIVED);
    }
}
