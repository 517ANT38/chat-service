package com.service.chatservice.domain.chat.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.service.chatservice.domain.chat.Chat;
import com.service.chatservice.domain.chat.dto.ChatDto;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperChat {
    
    @InheritInverseConfiguration
    Chat map(ChatDto dto);
    
    ChatDto map(Chat chat);
}
