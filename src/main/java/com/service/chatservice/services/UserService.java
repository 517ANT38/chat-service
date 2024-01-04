package com.service.chatservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.service.chatservice.app.ApiException;
import com.service.chatservice.app.NotFoundException;
import com.service.chatservice.domain.user.User;
import com.service.chatservice.repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepo userRepo;

    public User newUser(User user){
        
        if(userRepo.existsByUsername(user.getUsername())){
            throw new ApiException("A user with this username" + user.getUsername() + "exists", "BAD_REQUEST");
        }
            
        return userRepo.save(user);
    }

    public User findByName(String  username){
        return userRepo.findByUsername(username)
            .orElseThrow(() -> new NotFoundException("User not found with username=" + username));
    }

    public User findByid(long id){
        return userRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("User not found with id=" + id));
    }

    public List<User> findAll(){
        return userRepo.findAll();
    }
}
