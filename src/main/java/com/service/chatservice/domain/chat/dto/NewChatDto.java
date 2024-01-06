package com.service.chatservice.domain.chat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewChatDto {
    private String name;
    private boolean privateChat;
}
