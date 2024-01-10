package com.service.chatservice.domain.chat.dto;

import java.util.List;

import com.service.chatservice.domain.message.dto.MessageDto;
import com.service.chatservice.domain.user.dto.UserDto;

import lombok.Data;

@Data
public class ChatDto {
    private String name;
    private boolean privateChat;
    private List<UserDto> userDtos;
    private List<MessageDto> messages;
}
