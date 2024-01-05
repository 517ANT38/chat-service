package com.service.chatservice.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.service.chatservice.app.ApiException;
import com.service.chatservice.app.NotFoundException;
import com.service.chatservice.domain.user.User;
import com.service.chatservice.repositories.UserRepo;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    @PostConstruct
    @Transactional
    void defaultAdmin(){
        if(!userRepo.existsByUsername("admin")){
            var user = userRepo.save(User.builder()
                .username("admin")
                .name("admin")
                .password(encoder.encode("admin"))
                .role("ROLE_ADMIN")
                .enabled(true)
                .build());
                System.out.println(user);
        }
    }

    @Transactional
    public User newUser(User user){
        
        if(userRepo.existsByUsername(user.getUsername())){
            throw new ApiException("A user with this username" + user.getUsername() + "exists");
        }
            
        return userRepo.save(user.toBuilder()
            .password(encoder.encode(user.getPassword()))
            .build());
    }

    public User findByUsername(String  username){
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
