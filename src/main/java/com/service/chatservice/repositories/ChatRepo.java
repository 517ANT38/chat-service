package com.service.chatservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.chatservice.domain.chat.Chat;

@Repository
public interface ChatRepo extends JpaRepository<Chat,Long>{
    
}
