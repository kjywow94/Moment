package com.travelmaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.travelmaker.dto.Chat;


@CrossOrigin(origins = { "*" })
@Controller
public class MessagingController {
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@MessageMapping("/greetings")
	@SendTo("/topic/greetings")
	public void greeting(Chat chat) throws Exception {
		messagingTemplate.convertAndSend("/topic/greetings/" + chat.getRoomNumber(), chat.getContext() + "님이 입장하셨습니다.");
	}
	
	@MessageMapping("/goodbye")
	@SendTo("/topic/goodbye")
	public void goodbye(Chat chat) throws Exception {
		messagingTemplate.convertAndSend("/topic/goodbye/" + chat.getRoomNumber(), chat.getContext() + "님이 퇴장하셨습니다.");
	}
	
	@MessageMapping("/chat")
	@SendTo("/topic/chat")
	public void outChat(Chat chat) throws Exception {
		messagingTemplate.convertAndSend("/topic/chat/" + chat.getRoomNumber(), chat);
	}
	
	@MessageMapping("/privateChat")
	@SendTo("/topic/privateChat")
	public void privateChat(Chat chat) throws Exception {
		messagingTemplate.convertAndSend("/topic/privateChat/" + chat.getRoomNumber(), chat);
	}
}
