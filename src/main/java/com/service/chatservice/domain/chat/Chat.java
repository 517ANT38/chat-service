package com.service.chatservice.domain.chat;


import java.util.Set;

import com.service.chatservice.domain.message.Message;
import com.service.chatservice.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
@Entity
@Builder(toBuilder = true)
public class Chat {
    
    @Id
    private Long id;
    @Column(unique = true)
    private String name;
    private String ref;
    private boolean privateChat;
    @OneToMany(mappedBy = "chat")
    private Set<Message> messages;
    @ManyToMany(mappedBy = "chats")
    private Set<User> users;
}
