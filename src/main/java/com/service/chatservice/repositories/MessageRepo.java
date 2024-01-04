package com.service.chatservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.chatservice.domain.message.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message,Long> {
    
}
