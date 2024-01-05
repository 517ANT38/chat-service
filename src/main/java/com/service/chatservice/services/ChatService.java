package com.service.chatservice.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.service.chatservice.app.ApiException;
import com.service.chatservice.app.NotFoundException;
import com.service.chatservice.domain.chat.Chat;
import com.service.chatservice.repositories.ChatRepo;
import com.service.chatservice.repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {
    
    private final ChatRepo chatRepo;
    private final UserRepo userRepo;

    public Chat newChat(Chat chat){
        
        if(chatRepo.existsByName(chat.getName())){
            throw new ApiException("A chat with this name" + chat.getName() + "exists");
        }
            
        return chatRepo.save(chat);
    }

    public Chat findByName(String name){
        return chatRepo.findByName(name)
            .orElseThrow(() -> new NotFoundException("Chat not found with name=" + name));
    }

    public Chat findByid(long id){
        return chatRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("Chat not found with id=" + id));
    }

    @PreAuthorize("ADMIN")
    public List<Chat> findAll(){
        return chatRepo.findAll();
    }

    public void addUserIntoChat(long chatId,long userId){
        var chat = chatRepo.findById(chatId)
            .orElseThrow(() -> new NotFoundException("Chat not found with id=" + chatId));
        var user = userRepo.findById(userId)
            .orElseThrow(() -> new NotFoundException("User not found with id=" + userId));
        var setChat = Optional.ofNullable(chat.getUsers()).orElse(new HashSet<>());
        setChat.add(user);
        chat.setUsers(setChat);
        var setUser  = Optional.ofNullable(user.getChats()).orElse(new HashSet<>());
        setUser.add(chat);
        user.setChats(setUser);
        userRepo.save(user);
    }

    public void deleteUserIntoChat(long chatId,long userId){
        var chat = chatRepo.findById(chatId)
            .orElseThrow(() -> new NotFoundException("Chat not found with id=" + chatId));
        var user = userRepo.findById(userId)
            .orElseThrow(() -> new NotFoundException("User not found with id=" + userId));
        var setChat = Optional.ofNullable(chat.getUsers()).orElse(new HashSet<>());
        setChat.remove(user);
        chat.setUsers(setChat);
        var setUser  = Optional.ofNullable(user.getChats()).orElse(new HashSet<>());
        setUser.remove(chat);
        user.setChats(setUser);
        userRepo.save(user);
    }
}
