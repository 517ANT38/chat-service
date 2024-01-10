package com.service.chatservice.domain.message.dto;



import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NewMessageDto {
    private long chatId;
    private long userId;
    private String txt;
    private LocalDateTime sendAt;
}
