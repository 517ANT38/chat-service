package com.service.chatservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.chatservice.domain.chat.Chat;

@Repository
public interface ChatRepo extends JpaRepository<Chat,Long>{
    @Query("from Chat c left join fetch c.messages left join fetch c.users where c.name=:name")
    Optional<Chat> findByName(@Param("name") String name);

    @Query("from Chat c left join fetch c.messages left join fetch c.users where c.id=:id")
    Optional<Chat> findByid(@Param("id") Long id);      

    boolean existsByName(String name);
}
