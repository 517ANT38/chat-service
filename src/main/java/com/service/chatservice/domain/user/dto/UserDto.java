package com.service.chatservice.domain.user.dto;

import java.util.List;

import com.service.chatservice.domain.chat.dto.ChatDto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String name;
    private List<ChatDto> chats;
}
