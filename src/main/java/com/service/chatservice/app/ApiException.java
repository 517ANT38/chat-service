package com.service.chatservice.app;


public class ApiException extends RuntimeException {
    

    public ApiException(String message){
        super(message);
        
    }
}