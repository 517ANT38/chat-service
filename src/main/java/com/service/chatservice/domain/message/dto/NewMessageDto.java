package com.service.chatservice.domain.message.dto;



import lombok.Data;

@Data
public class NewMessageDto {
    private long chatId;
    private String txt;
}
