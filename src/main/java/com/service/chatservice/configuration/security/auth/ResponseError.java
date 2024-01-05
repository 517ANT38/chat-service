package com.service.chatservice.configuration.security.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseError {
    private String message;
    private String timestamp;
}
