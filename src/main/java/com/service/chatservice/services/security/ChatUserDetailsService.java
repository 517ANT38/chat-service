package com.service.chatservice.services.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.service.chatservice.configuration.security.auth.LocalUserDetails;
import com.service.chatservice.repositories.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChatUserDetailsService implements UserDetailsService {

    private final UserRepo aRepo;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var v = aRepo.findByUsername(s);

        if (v.isEmpty())
            throw new UsernameNotFoundException("User not found");
        var user = v.get();
        return LocalUserDetails.builder()
            .id(user.getId())
            .username(user.getUsername())
            .password(user.getPassword())
            .role(user.getRole())
            .enabled(user.isEnabled())
            .build();
    }
}