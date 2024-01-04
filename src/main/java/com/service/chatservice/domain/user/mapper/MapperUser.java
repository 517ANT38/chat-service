package com.service.chatservice.domain.user.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.service.chatservice.domain.user.User;
import com.service.chatservice.domain.user.dto.NewUserDto;
import com.service.chatservice.domain.user.dto.UserDto;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperUser {
    
    @InheritInverseConfiguration
    User map(NewUserDto dto);

    UserDto map(User user);
}
