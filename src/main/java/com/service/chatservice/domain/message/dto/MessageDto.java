package com.service.chatservice.domain.message.dto;

import java.time.LocalDateTime;

import com.service.chatservice.domain.chat.dto.ChatDto;
import com.service.chatservice.domain.user.dto.UserDto;

import lombok.Data;

@Data
public class MessageDto {
    private Long id;
    private UserDto user;
    private ChatDto chat;
    private String txt;
    private LocalDateTime sendAt;
}
