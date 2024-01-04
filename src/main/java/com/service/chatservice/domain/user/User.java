package com.service.chatservice.domain.user;



import java.util.Set;

import org.hibernate.annotations.Cascade;

import com.service.chatservice.domain.chat.Chat;
import com.service.chatservice.domain.message.Message;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import static org.hibernate.annotations.CascadeType.ALL;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class User {
    @Id
    private Long id;
    @Column(unique = true)
    private String username;
    private String name;
    private String password;
    private String role;
    @OneToMany(mappedBy = "user")
    private Set<Message> messages;
    private boolean enabled;
    @ManyToMany
    @JoinTable(
        name="chat_user",
        joinColumns = @JoinColumn(name = "id_chat"),
        inverseJoinColumns = @JoinColumn(name = "id_user")
    )    
    @Cascade({ALL})
    private Set<Chat> chats;
}
