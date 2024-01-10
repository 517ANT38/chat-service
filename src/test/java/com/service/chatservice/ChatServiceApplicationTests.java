package com.service.chatservice;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import com.service.chatservice.domain.chat.dto.ChatDto;
import com.service.chatservice.domain.chat.dto.NewChatDto;
import com.service.chatservice.domain.security.AuthDto;
import com.service.chatservice.domain.security.AuthResultDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
class ChatControllerTests {

	@Autowired TestRestTemplate template;

	private String token;

	@BeforeAll
	void addToken(){
		var auth = AuthDto.builder()
			.username("admin")
			.password("admin")
			.build();
		var authRes = template.postForObject("/login", auth, AuthResultDto.class);
		
		token = authRes.getToken();
	}

	@Test
	void addChat(){
		var chat = NewChatDto.builder()
			.name("test_chat")
			.build();
		
		var res = template.postForEntity("/api/chats/new", chat, ChatDto.class);
		var body = res.getBody();

		assertThat(res.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
		assertTrue(body != null);
		assertThat(body.getName()).isEqualTo(chat.getName());
	}

}
