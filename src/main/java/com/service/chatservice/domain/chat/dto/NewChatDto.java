package com.service.chatservice.domain.chat.dto;

import lombok.Data;

@Data
public class NewChatDto {
    private String name;
    private boolean privateChat;
}
