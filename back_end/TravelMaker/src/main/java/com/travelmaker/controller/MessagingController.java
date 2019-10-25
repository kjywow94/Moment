package com.travelmaker.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.travelmaker.dto.Chat;
@Controller
public class MessagingController {
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Chat greeting(Chat message) throws Exception {
		Thread.sleep(100); // delay
		Chat c = new Chat();
		c.setContext("H");
		return c;
	}
	
	@MessageMapping("/out")
	@SendTo("/topic/out")
	public Chat outChat(Chat chat) throws Exception {
		return chat;
	}
	
	@MessageMapping("/in")
	@SendTo("/topic/in")
	public Chat inChat(Chat chat) throws Exception {
		return chat;
	}
}
