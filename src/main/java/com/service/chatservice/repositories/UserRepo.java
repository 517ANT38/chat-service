package com.service.chatservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.chatservice.domain.user.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    
    Optional<User> findByUsername(String username);

}
