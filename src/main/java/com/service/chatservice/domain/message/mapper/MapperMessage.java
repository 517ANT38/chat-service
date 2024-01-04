package com.service.chatservice.domain.message.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.service.chatservice.domain.message.Message;
import com.service.chatservice.domain.message.dto.MessageDto;
import com.service.chatservice.domain.message.dto.NewMessageDto;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperMessage {

    @InheritInverseConfiguration
    Message map(NewMessageDto dto);

    MessageDto map(Message message);
}
