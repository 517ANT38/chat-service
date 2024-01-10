package com.service.chatservice.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.chatservice.domain.message.Message;
import com.service.chatservice.domain.message.MessageStatus;

@Repository
public interface MessageRepo extends JpaRepository<Message,Long> {
    @Query("select count(*) from Message m where m.chat.id=:chatId and m.status=:status")
    long countNewMessage(@Param("chatId") long chatId,@Param("status") MessageStatus status);
    @Query("from Message m where m.chat.id=:chatId")
    Set<Message> findMessagesByChatId(@Param("chatId") long chatId);
}
