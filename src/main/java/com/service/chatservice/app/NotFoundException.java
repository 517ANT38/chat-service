package com.service.chatservice.app;

public class NotFoundException extends ApiException {

    public NotFoundException(String message) {
        super(message, "NOT_FOUND");
    }
    
}
